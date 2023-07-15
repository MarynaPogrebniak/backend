package de.ait.rentacar.services.impl;

import de.ait.rentacar.models.Car;
import de.ait.rentacar.repositories.CarsRepository;
import de.ait.rentacar.services.CarsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarsServiceImpl implements CarsService {

    private final CarsRepository carsRepository;

    public CarsServiceImpl(CarsRepository carsRepository) {
        this.carsRepository = carsRepository;
    }

    @Override
    public List<Car> getAllCars() {
        return carsRepository.findAll();
    }
}
