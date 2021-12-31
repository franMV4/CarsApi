package com.svalero.carsapi.service;

import com.svalero.carsapi.domain.Car;
import com.svalero.carsapi.domain.Reparation;
import com.svalero.carsapi.domain.dto.CarDTO;
import com.svalero.carsapi.exception.CarNotFoundException;

import java.util.List;


public interface CarService {
    List<Car> findAllCars();
    Car findCar(long id) throws CarNotFoundException;





    Car addCar(CarDTO carDto) throws CarNotFoundException;

    Car deleteCar(long id) throws CarNotFoundException;
    Car modifyCar(long id, Car car) throws CarNotFoundException ;
    Car patchCar(long id, String brand) throws CarNotFoundException ;

}
