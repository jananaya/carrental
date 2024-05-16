package com.carrental.inventory.controllers;

import com.carrental.inventory.dtos.CarDto;
import com.carrental.inventory.dtos.CreateCarDto;
import com.carrental.inventory.services.CarsService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uk.gov.hmrc.play.http.NotFoundException;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarsController {
    private final CarsService carsService;

    public CarsController(CarsService carsService) {
        this.carsService = carsService;
    }

    @GetMapping("/available")
    public ResponseEntity<List<CarDto>> getAvailableCars() {
        return ResponseEntity.ok(carsService.getAvailableCars());
    }

    @PatchMapping("/booking/{guid}")
    public ResponseEntity<?> reserveCar(@PathVariable String guid) throws NotFoundException {
        carsService.reserveCar(guid);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{guid}")
    public ResponseEntity<CarDto> getCarById(@PathVariable String guid) throws NotFoundException {
        return ResponseEntity.ok(carsService.getCarById(guid));
    }

    @PostMapping
    public ResponseEntity<CarDto> createCar(@RequestBody @Valid CreateCarDto createCarDto) {
        CarDto carDto = carsService.createCar(createCarDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(carDto.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(carDto);
    }
}
