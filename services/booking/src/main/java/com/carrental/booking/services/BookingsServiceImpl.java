package com.carrental.booking.services;

import com.carrental.booking.dtos.BookingDto;
import com.carrental.booking.dtos.CarDto;
import com.carrental.booking.dtos.CreateBookingDto;
import com.carrental.booking.entities.Booking;
import com.carrental.booking.enums.EBookingStatus;
import com.carrental.booking.repositories.BookingsRepository;
import com.carrental.booking.utils.DateTimeParser;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import uk.gov.hmrc.play.http.BadRequestException;
import uk.gov.hmrc.play.http.NotFoundException;

import java.time.LocalDateTime;

@Service
public class BookingsServiceImpl implements BookingsService {
    private final BookingsRepository bookingsRepository;
    private final CarsInventoryService carsInventoryService;
    private final MessageService messageService;


    public BookingsServiceImpl(BookingsRepository bookingsRepository, CarsInventoryService carsInventoryService,
                               MessageService messageService) {
        this.bookingsRepository = bookingsRepository;
        this.carsInventoryService = carsInventoryService;
        this.messageService = messageService;
    }

    @Override
    @Transactional
    public BookingDto createBooking(CreateBookingDto createBookingDto) throws BadRequestException {
        CarDto carDto = carsInventoryService.getCarById(createBookingDto.getCarId())
                .orElseThrow(() -> new BadRequestException(messageService.getMessage("validation.carId.CarDoesNotExist")));

        if (!carDto.getAvailable()) {
            throw new BadRequestException(messageService.getMessage("validation.carId.CarDoesNotAvailable"));
        }

        Booking booking = getBooking(createBookingDto);

        if (booking.getStartDate().isBefore(LocalDateTime.now())) {
            throw new BadRequestException(messageService.getMessage("validation.startDate.NotEarlierThanToday"));
        }

        if (booking.getEndDate() != null && booking.getStartDate().isAfter(booking.getEndDate())) {
            throw new BadRequestException(messageService.getMessage("validation.endDate.NotEarlierThanStartDate"));
        }

        bookingsRepository.save(booking);

        boolean carReserved = carsInventoryService.reserveCar(booking.getCarId());

        if (!carReserved) {
            throw new IllegalStateException(messageService.getMessage("validation.createBooking.CarReservationFailed"));
        }

        return getBookingDto(booking);
    }

    @Override
    public BookingDto getBookingById(String guid) throws NotFoundException {
        Booking booking = bookingsRepository.findById(guid)
                .orElseThrow(() -> new NotFoundException(Booking.class.getName()));

        return getBookingDto(booking);
    }

    private static BookingDto getBookingDto(Booking booking) {
        String endDate = booking.getEndDate() != null ?
                booking.getEndDate().toString() : null;

        BookingDto bookingDto = BookingDto.builder()
                .id(booking.getId())
                .customerId(booking.getCustomerId())
                .startDate(booking.getStartDate().toString())
                .endDate(endDate)
                .carId(booking.getCarId())
                .status(booking.getStatus().name())
                .build();

        return bookingDto;
    }

    private static Booking getBooking(CreateBookingDto createBookingDto) {
        Booking booking = Booking
                .builder()
                .carId(createBookingDto.getCarId())
                .status(EBookingStatus.COMPLETED)
                .startDate(DateTimeParser.parse(createBookingDto.getStartDate()))
                .customerId(createBookingDto.getCustomerId())
                .build();
        String endDate = createBookingDto.getEndDate();

        if (endDate != null) {
            booking.setEndDate(DateTimeParser.parse(endDate));
        }

        return booking;
    }
}
