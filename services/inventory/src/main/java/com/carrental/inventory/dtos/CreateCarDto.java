package com.carrental.inventory.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCarDto {
    @NotBlank(message = "{validation.model.NotBlank}")
    private String model;
    @NotBlank(message = "{validation.make.NotBlank}")
    private String make;
    @NotNull(message = "{validation.available.NotNull}")
    private Boolean available;
}
