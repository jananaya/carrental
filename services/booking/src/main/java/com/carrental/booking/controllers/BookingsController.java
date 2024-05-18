package com.carrental.booking.controllers;

import com.carrental.booking.dtos.BookingDto;
import com.carrental.booking.dtos.CreateBookingDto;
import com.carrental.booking.services.BookingsService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uk.gov.hmrc.play.http.BadRequestException;
import uk.gov.hmrc.play.http.NotFoundException;

import java.net.URI;

@RestController
@RequestMapping("/api/bookings")
public class BookingsController {
    private final BookingsService bookingsService;

    public BookingsController(BookingsService bookingsService) {
        this.bookingsService = bookingsService;
    }

    @PostMapping
    public ResponseEntity<BookingDto> createBooking(@RequestBody @Valid CreateBookingDto createBookingDto) throws BadRequestException {
        BookingDto bookingDto = bookingsService.createBooking(createBookingDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(bookingDto.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(bookingDto);
    }

    @GetMapping("/{guid}")
    public ResponseEntity<BookingDto> getBookingById(@PathVariable String guid) throws NotFoundException {
        BookingDto bookingDto = bookingsService.getBookingById(guid);
        return ResponseEntity.ok(bookingDto);
    }
}
