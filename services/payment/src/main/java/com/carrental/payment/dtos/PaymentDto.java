package com.carrental.payment.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class PaymentDto extends CreatePaymentDto {
    @NotBlank(message = "{validation.paymentId.NotBlank}")
    private String id;
}
