package com.carrental.payment.entities;

import com.carrental.payment.enums.EPaymentStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "booking_id")
    private String bookingId;
    @Column(name = "credit_card")
    private String creditCard;
    private Float amount;
    @Enumerated(EnumType.STRING)
    private EPaymentStatus status;
    @Column(name = "transaction_id")
    private String transactionId;
}
