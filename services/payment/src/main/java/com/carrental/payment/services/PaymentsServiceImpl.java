package com.carrental.payment.services;

import com.carrental.payment.dtos.CreatePaymentDto;
import com.carrental.payment.dtos.PaymentDto;
import com.carrental.payment.entities.Payment;
import com.carrental.payment.enums.EPaymentStatus;
import com.carrental.payment.repositories.PaymentsRepository;
import org.springframework.stereotype.Service;
import uk.gov.hmrc.play.http.NotFoundException;

@Service
public class PaymentsServiceImpl implements PaymentsService {
    private final PaymentsRepository paymentsRepository;

    public PaymentsServiceImpl(PaymentsRepository paymentsRepository) {
        this.paymentsRepository = paymentsRepository;
    }

    @Override
    public PaymentDto getPaymentById(String guid) throws NotFoundException {
        Payment payment = paymentsRepository.findById(guid)
                .orElseThrow(() -> new NotFoundException(Payment.class.getName()));

        return getPaymentDto(payment);
    }

    @Override
    public PaymentDto processPayment(CreatePaymentDto createPaymentDto) {
        Payment payment = getPayment(createPaymentDto);

        paymentsRepository.saveAndFlush(payment);

        return getPaymentDto(payment);
    }

    private static Payment getPayment(CreatePaymentDto createPaymentDto) {
        return Payment
                .builder()
                .bookingId(createPaymentDto.getBookingId())
                .creditCard(createPaymentDto.getCreditCard())
                .amount(createPaymentDto.getAmount())
                .status(EPaymentStatus.valueOf(createPaymentDto.getStatus()))
                .transactionId(createPaymentDto.getTransactionId())
                .build();
    }

    private static PaymentDto getPaymentDto(Payment payment) {
        return PaymentDto
                .builder()
                .id(payment.getId())
                .bookingId(payment.getBookingId())
                .creditCard(payment.getCreditCard())
                .amount(payment.getAmount())
                .status(payment.getStatus().toString())
                .transactionId(payment.getTransactionId())
                .build();
    }
}
