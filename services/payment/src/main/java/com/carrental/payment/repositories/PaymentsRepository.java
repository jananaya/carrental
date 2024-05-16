package com.carrental.payment.repositories;

import com.carrental.payment.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentsRepository extends JpaRepository<Payment, String> {

}
