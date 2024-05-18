package com.carrental.booking.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookingDto {
    @NotBlank(message = "{validation.carId.NotBlank}")
    protected String carId;
    @NotBlank(message = "{validation.customerId.NotBlank}")
    protected String customerId;
    @NotBlank(message = "{validation.starDate.NotBlank}")
    protected String startDate;
    protected String endDate;
}
