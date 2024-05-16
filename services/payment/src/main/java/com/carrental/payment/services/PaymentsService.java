package com.carrental.payment.services;

import com.carrental.payment.dtos.CreatePaymentDto;
import com.carrental.payment.dtos.PaymentDto;
import uk.gov.hmrc.play.http.NotFoundException;

public interface PaymentsService {
    PaymentDto getPaymentById(String guid) throws NotFoundException;

    PaymentDto processPayment(CreatePaymentDto createPaymentDto);
}
