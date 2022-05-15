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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public Flux<Car> findAllCars() {
        return (Flux<Car>) carRepository.findAll();
    }

    @Override
    public Mono<Car> findCar(long id) throws CarNotFoundException {
        return carRepository.findById(id)
                .onErrorReturn(new Car());
    }



    @Override
    public Mono<Car> addCar(CarDTO carDto) throws CarNotFoundException {
        Mono<User> user = userRepository.findById(carDto.getUser()).onErrorReturn(new User());

        ModelMapper mapper = new ModelMapper();
        Car car = mapper.map(carDto, Car.class);

        car.setUser(user.block());
        return carRepository.save(car);}

    @Override
    public Mono<Car> deleteCar(long id) throws CarNotFoundException {
        Mono<Car> car = carRepository.findById(id).onErrorReturn(new Car());
        carRepository.delete(car.block());
        return car;
    }

    @Override
    public Mono<Car> modifyCar(long id, Car newCar) throws CarNotFoundException {
        Mono<Car> car = carRepository.findById(id).onErrorReturn(new Car());
        car.block().setBrand(newCar.getBrand());
        car.block().setColor(newCar.getColor());
        car.block().setModel(newCar.getModel());
        car.block().setHorsePower(newCar.getHorsePower());
        car.block().setSeats(newCar.getSeats());

        return carRepository.save(car.block());
    }

    @Override
    public Mono<Car> patchCar(long id, String brand) throws CarNotFoundException {
        Mono<Car> car = carRepository.findById(id)
                .onErrorReturn(new Car());
        car.block().setBrand(brand);
        return carRepository.save(car.block());
    }


}
