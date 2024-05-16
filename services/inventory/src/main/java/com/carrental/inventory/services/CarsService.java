package com.carrental.inventory.services;

import com.carrental.inventory.dtos.CarDto;
import com.carrental.inventory.dtos.CreateCarDto;
import uk.gov.hmrc.play.http.NotFoundException;

import java.util.List;

public interface CarsService {
    List<CarDto> getAvailableCars();

    void reserveCar(String guid) throws NotFoundException;

    CarDto getCarById(String guid) throws NotFoundException;

    CarDto createCar(CreateCarDto createCarDto);
}
