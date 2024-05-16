package com.carrental.inventory.repositories;

import com.carrental.inventory.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarsRepository extends JpaRepository<Car, String> {
    List<Car> findByAvailable(Boolean available);
}
