package com.svalero.carsapi.service;

import com.svalero.carsapi.domain.Car;
import com.svalero.carsapi.domain.Reparation;
import com.svalero.carsapi.domain.dto.CarDTO;
import com.svalero.carsapi.exception.CarNotFoundException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


public interface CarService {
    Flux<Car> findAllCars();
    Mono<Car> findCar(long id) throws CarNotFoundException;





    Mono<Car> addCar(CarDTO carDto) throws CarNotFoundException;

    Mono<Car> deleteCar(long id) throws CarNotFoundException;
    Mono<Car> modifyCar(long id, Car car) throws CarNotFoundException ;
    Mono<Car> patchCar(long id, String brand) throws CarNotFoundException ;

}
