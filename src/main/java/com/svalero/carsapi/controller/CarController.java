package com.svalero.carsapi.controller;

import com.svalero.carsapi.domain.Car;
import com.svalero.carsapi.domain.Reparation;
import com.svalero.carsapi.domain.dto.CarDTO;
import com.svalero.carsapi.exception.CarNotFoundException;
import com.svalero.carsapi.exception.ErrorResponse;
import com.svalero.carsapi.service.CarService;
import com.svalero.carsapi.service.ReparationService;
import com.svalero.carsapi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Flux<Car>> getCars() {
        Flux<Car> cars;


        cars = carService.findAllCars();

        return ResponseEntity.ok(cars);
    }


    @GetMapping("/car/{carId}/reparations")
    public ResponseEntity<Flux<Reparation>> getReparations(@PathVariable long carId,
                                                           @RequestParam(name = "cost", defaultValue = "0") int cost)
            throws CarNotFoundException {
        Flux<Reparation> reparationList = null;

        Mono<Car> car = carService.findCar(carId);
        if (cost != 0) {
            reparationList = reparationService.findReparations(car.block(), cost);
        } else {
            reparationList = reparationService.findReparations(car.block());
        }

        return ResponseEntity.ok(reparationList);
    }

    @DeleteMapping("/car/{id}")
    public ResponseEntity<Mono<Car>> removeCar(@PathVariable long id) throws CarNotFoundException {
        Mono<Car> car = carService.deleteCar(id);
        return ResponseEntity.ok(car);
    }

    @PostMapping("/cars")
    public ResponseEntity<?> addCar(@Valid @RequestBody CarDTO carDto) throws CarNotFoundException {
        Mono<Car> newCar = carService.addCar(carDto);
        return ResponseEntity.ok(newCar.block());
    }

    @PutMapping("/car/{id}")
    public ResponseEntity<Mono<Car>> modifyCar(@RequestBody Car car, @PathVariable long id) throws CarNotFoundException {
        Mono<Car> newCar = carService.modifyCar(id, car);
        return ResponseEntity.ok(newCar);
    }

    @PatchMapping("/car/{id}")
    public ResponseEntity<Mono<Car>> patchCar(@PathVariable long id, @RequestBody String brand) throws CarNotFoundException {
       // logger.info("Start PatchCar " + id);
        Mono<Car> car = carService.patchCar(id, brand);
      //  logger.info("End patchCar " + id);
        return ResponseEntity.ok(car);
    }

    @ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCarNotFoundException(CarNotFoundException bnfe) {
        ErrorResponse errorResponse = ErrorResponse.generalError(404, bnfe.getMessage());
        logger.error(bnfe.getMessage(), bnfe);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // TODO Más tipos de excepciones que puedan generar errores

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        ErrorResponse errorResponse = ErrorResponse.generalError(999, "Internal server error");
        logger.error(exception.getMessage(), exception);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException manve) {
        Map<String, String> errors = new HashMap<>();
        manve.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        return ResponseEntity.badRequest().body(ErrorResponse.validationError(errors));
    }

}
