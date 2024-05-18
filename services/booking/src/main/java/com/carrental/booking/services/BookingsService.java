package com.carrental.booking.services;

import com.carrental.booking.dtos.BookingDto;
import com.carrental.booking.dtos.CreateBookingDto;
import uk.gov.hmrc.play.http.BadRequestException;
import uk.gov.hmrc.play.http.NotFoundException;

public interface BookingsService {
    BookingDto createBooking(CreateBookingDto createBookingDto) throws BadRequestException;

    BookingDto getBookingById(String guid) throws NotFoundException;
}
