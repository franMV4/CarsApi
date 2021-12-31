package com.svalero.carsapi.service;

import com.svalero.carsapi.domain.Car;
import com.svalero.carsapi.domain.Reparation;
import com.svalero.carsapi.domain.User;
import com.svalero.carsapi.domain.dto.CarDTO;
import com.svalero.carsapi.exception.CarNotFoundException;
import com.svalero.carsapi.repository.CarRepository;
import com.svalero.carsapi.repository.ReparationRepository;
import com.svalero.carsapi.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService{

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReparationRepository reparationRepository;

    @Override
    public List<Car> findAllCars() {
        return (List<Car>) carRepository.findAll();
    }

    @Override
    public Car findCar(long id) throws CarNotFoundException {
        return carRepository.findById(id)
                .orElseThrow(CarNotFoundException::new);
    }



    @Override
    public Car addCar(CarDTO carDto) throws CarNotFoundException {
        User user = userRepository.findById(carDto.getUser())
            .orElseThrow(CarNotFoundException::new);

        ModelMapper mapper = new ModelMapper();
        Car car = mapper.map(carDto, Car.class);

        car.setUser(user);
        return carRepository.save(car);}

    @Override
    public Car deleteCar(long id) throws CarNotFoundException {
        Car car = carRepository.findById(id)
                .orElseThrow(CarNotFoundException::new);
        carRepository.delete(car);
        return car;
    }

    @Override
    public Car modifyCar(long id, Car newCar) throws CarNotFoundException {
        Car car = carRepository.findById(id)
                .orElseThrow(CarNotFoundException::new);
        car.setBrand(newCar.getBrand());

        return carRepository.save(car);
    }

    @Override
    public Car patchCar(long id, String brand) throws CarNotFoundException {
        Car car = carRepository.findById(id)
                .orElseThrow(CarNotFoundException::new);
        car.setBrand(brand);
        return carRepository.save(car);
    }


}
