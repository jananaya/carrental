package com.carrental.booking.clients;

import com.carrental.booking.dtos.CarDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "carInventory", url = "${spring.cloud.openfeign.client.config.carInventory.url}")
public interface CarsInventoryClient {
    @GetMapping("/{carId}")
    CarDto getCarById(@PathVariable String carId);

    @PatchMapping("/booking/{carId}")
    void reserveCar(@PathVariable String carId);
}
