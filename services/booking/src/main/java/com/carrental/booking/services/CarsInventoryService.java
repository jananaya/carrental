package com.carrental.booking.services;

import com.carrental.booking.dtos.CarDto;

import java.util.Optional;

public interface CarsInventoryService {
    Optional<CarDto> getCarById(String carId);

    boolean reserveCar(String carId);
}
