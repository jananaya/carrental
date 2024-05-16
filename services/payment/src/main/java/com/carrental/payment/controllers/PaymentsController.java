package com.carrental.payment.controllers;

import com.carrental.payment.dtos.CreatePaymentDto;
import com.carrental.payment.dtos.PaymentDto;
import com.carrental.payment.services.PaymentsService;
import jakarta.validation.Valid;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uk.gov.hmrc.play.http.NotFoundException;

import java.net.URI;

@RestController
@RequestMapping("/api/payments")
public class PaymentsController {
    private final PaymentsService paymentsService;

    public PaymentsController(PaymentsService paymentsService) {
        this.paymentsService = paymentsService;
    }

    @PostMapping
    public ResponseEntity<PaymentDto> processPayment(@RequestBody @Valid CreatePaymentDto createPaymentDto) {
        PaymentDto paymentDto = paymentsService.processPayment(createPaymentDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(paymentDto.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(paymentDto);
    }

    @GetMapping("/{guid}")
    public ResponseEntity<PaymentDto> getPaymentById(@PathVariable String guid) throws NotFoundException {
        PaymentDto paymentDto = paymentsService.getPaymentById(guid);
        return ResponseEntity.ok(paymentDto);
    }
}
