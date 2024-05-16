package com.carrental.inventory.services;

import com.carrental.inventory.dtos.CarDto;
import com.carrental.inventory.dtos.CreateCarDto;
import com.carrental.inventory.entities.Car;
import com.carrental.inventory.repositories.CarsRepository;
import org.springframework.stereotype.Service;
import uk.gov.hmrc.play.http.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarsServiceImpl implements CarsService {
    private final CarsRepository carsRepository;

    public CarsServiceImpl(CarsRepository carsRepository) {
        this.carsRepository = carsRepository;
    }

    @Override
    public List<CarDto> getAvailableCars() {
        List<Car> availableCars = carsRepository.findByAvailable(true);

        return availableCars.stream()
                .map((c) -> getCarDto(c))
                .collect(Collectors.toList());
    }

    @Override
    public void reserveCar(String guid) throws NotFoundException {
        Car car = carsRepository.findById(guid)
                .orElseThrow(() -> new NotFoundException(Car.class.getName()));

        car.setAvailable(false);

        carsRepository.saveAndFlush(car);
    }

    @Override
    public CarDto getCarById(String guid) throws NotFoundException {
        Car car = carsRepository.findById(guid)
                .orElseThrow(() -> new NotFoundException(Car.class.getName()));

        return getCarDto(car);
    }

    @Override
    public CarDto createCar(CreateCarDto createCarDto) {
        Car car = getCar(createCarDto);

        carsRepository.saveAndFlush(car);

        return getCarDto(car);
    }

    private static Car getCar(CreateCarDto createCarDto) {
        return Car
                .builder()
                .model(createCarDto.getModel())
                .make(createCarDto.getMake())
                .available(createCarDto.getAvailable())
                .build();
    }

    private static CarDto getCarDto(Car car) {
        return CarDto
                .builder()
                .id(car.getId())
                .model(car.getModel())
                .make(car.getMake())
                .available(car.getAvailable())
                .build();
    }
}
