package com.carrental.booking.services;

import com.carrental.booking.clients.CarsInventoryClient;
import com.carrental.booking.dtos.CarDto;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarsInventoryServiceImpl implements CarsInventoryService {
    private final CarsInventoryClient carsInventoryClient;

    public CarsInventoryServiceImpl(CarsInventoryClient carsInventoryClient) {
        this.carsInventoryClient = carsInventoryClient;
    }

    @Override
    public Optional<CarDto> getCarById(String carId) {
        try {
            CarDto carDto = carsInventoryClient.getCarById(carId);
            return Optional.of(carDto);
        } catch (FeignException e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean reserveCar(String carId) {
        try {
            carsInventoryClient.reserveCar(carId);
            return true;
        } catch (FeignException e) {
            return false;
        }
    }
}
