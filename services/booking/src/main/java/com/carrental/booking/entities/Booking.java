package com.carrental.booking.entities;

import com.carrental.booking.enums.EBookingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "car_id")
    private String carId;
    @Column(name = "customer_id")
    private String customerId;
    @Enumerated(EnumType.STRING)
    private EBookingStatus status;
    @Column(name = "start_date")
    private LocalDateTime startDate;
    @Column(name = "end_date", nullable = true)
    private LocalDateTime endDate;
}
