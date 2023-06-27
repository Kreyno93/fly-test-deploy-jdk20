package de.neuefische.backend.service;

import de.neuefische.backend.model.Car;
import de.neuefische.backend.model.CarDTO;
import de.neuefische.backend.repo.CarRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class CarService {

    private final CarRepo carRepo;

    public CarService(CarRepo carRepo) {
        this.carRepo = carRepo;
    }

    public Car saveCar(CarDTO car) {
        Car newCar = new Car(UUID.randomUUID().toString(), car.getBrand(), car.getModel());
        return carRepo.save(newCar);
    }

    public List<Car> findAllCars() {
        return carRepo.findAll();
    }

    public Car findCarById(String id) {
        return carRepo.findById(id).orElseThrow(() -> new NoSuchElementException("No Element found with ID: "+id));
    }
}
