package com.svalero.carsapi.controller;

import com.svalero.carsapi.domain.Car;
import com.svalero.carsapi.domain.Reparation;
import com.svalero.carsapi.domain.dto.CarDTO;
import com.svalero.carsapi.exception.CarNotFoundException;
import com.svalero.carsapi.service.CarService;
import com.svalero.carsapi.service.ReparationService;
import com.svalero.carsapi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {
    private final Logger logger = LoggerFactory.getLogger(CarController.class);

    @Autowired
    private CarService carService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReparationService reparationService;



    @GetMapping("/cars")
    public List<Car> getCars() {
        logger.info("Start getCars");
        List<Car> cars;
        cars = carService.findAllCars();
        logger.info("End getCars");
        return cars;
    }


    @GetMapping("/car/{carId}/reparations")
    public List<Reparation> getReparations(@PathVariable long carId,
                                           @RequestParam(name = "cost", defaultValue = "0") int cost)
            throws CarNotFoundException {
        List<Reparation> reparationList = null;
        logger.info("Start getCarsByReparations");

        Car car = carService.findCar(carId);
        if (cost != 0) {
            reparationList = reparationService.findReparations(car, cost);
        } else {
            reparationList = reparationService.findReparations(car);
        }
        logger.info("End getCarsByReparations");

        return reparationList;
    }

    @DeleteMapping("/car/{id}")
    public Car removeCar(@PathVariable long id) throws CarNotFoundException {
        logger.info("Start removeCar" + id);
        Car car = carService.deleteCar(id);
        logger.info("End removeCar" + id);
        return car;
    }

    @PostMapping("/cars")
    public Car addCar(@RequestBody CarDTO carDto) throws CarNotFoundException {
        logger.info("Start addCar");
        return carService.addCar(carDto);
    }

    @PutMapping("/car/{id}")
    public Car modifyCar(@RequestBody Car car, @PathVariable long id) throws CarNotFoundException {
        logger.info("Start modifyCar" + id);
        Car newCar = carService.modifyCar(id, car);
        logger.info("End modifyCar" + id);
        return newCar;
    }

    @PatchMapping("/car/{id}")
    public Car patchCar(@PathVariable long id, @RequestBody String brand) throws CarNotFoundException {
        logger.info("Start PatchCar " + id);
        Car car = carService.patchCar(id, brand);
         logger.info("End patchCar " + id);
        return car;
    }
}
