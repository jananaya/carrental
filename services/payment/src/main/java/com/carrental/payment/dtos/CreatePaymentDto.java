package com.carrental.payment.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentDto {
    @NotBlank(message = "{validation.creditCard.NotBlank}")
    protected String creditCard;

    @NotBlank(message = "{validation.bookingId.NotBlank}")
    protected String bookingId;

    @NotNull(message = "{validation.amount.NotNull}")
    protected Float amount;

    @NotBlank(message = "{validation.status.NotBlank}")
    protected String status;

    @NotBlank(message = "{validation.transactionId.NotBlank}")
    protected String transactionId;
}