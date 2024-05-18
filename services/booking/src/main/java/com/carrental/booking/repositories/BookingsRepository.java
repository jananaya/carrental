package com.carrental.booking.repositories;

import com.carrental.booking.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingsRepository extends JpaRepository<Booking, String> {
}
