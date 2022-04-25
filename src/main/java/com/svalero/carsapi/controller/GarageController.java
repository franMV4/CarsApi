package com.svalero.carsapi.controller;

import com.svalero.carsapi.domain.Car;
import com.svalero.carsapi.domain.Garage;
import com.svalero.carsapi.domain.User;
import com.svalero.carsapi.domain.dto.GarageDTO;
import com.svalero.carsapi.domain.dto.UserDTO;
import com.svalero.carsapi.exception.CarNotFoundException;
import com.svalero.carsapi.exception.ErrorResponse;
import com.svalero.carsapi.exception.GarageNotFoundException;
import com.svalero.carsapi.service.CarService;
import com.svalero.carsapi.service.GarageService;
import com.svalero.carsapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class GarageController {

    private final Logger logger = LoggerFactory.getLogger(GarageController.class);

    @Autowired
    private GarageService garageService;



    @GetMapping("/garages")
    public ResponseEntity<Flux<Garage>> getGarage() {
        Flux<Garage> garages;


        garages = garageService.findAllGarages();

        return ResponseEntity.ok(garages);
    }

    @DeleteMapping("/garage/{id}")
    public ResponseEntity<Mono<Garage>> removeGarage(@PathVariable long id) throws GarageNotFoundException {
        Mono<Garage> garage = garageService.deleteGarage(id);
        return ResponseEntity.ok(garage);
    }
    @PostMapping("/garages")
    public ResponseEntity<?> addGarage(@Valid @RequestBody GarageDTO garageDto) throws GarageNotFoundException {
        Mono<Garage> newGarage = garageService.addGarage(garageDto);
        return ResponseEntity.ok(newGarage.block());

    }

    @PutMapping("/garage/{id}")
    public ResponseEntity<Mono<Garage>> modifyGarage(@RequestBody Garage garage, @PathVariable long id) throws GarageNotFoundException {
        Mono<Garage> newGarage = garageService.modifyGarage(id, garage);
        return ResponseEntity.ok(newGarage);
    }

    @PatchMapping("/garage/{id}")
    public ResponseEntity<Mono<Garage>> patchGarage(@PathVariable long id, @RequestBody String postalCode) throws GarageNotFoundException {
        // logger.info("Start PatchCar " + id);
        Mono<Garage> garage = garageService.patchGarage(id, postalCode);
        //  logger.info("End patchCar " + id);
        return ResponseEntity.ok(garage);
    }

    @ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleGarageNotFoundException(GarageNotFoundException bnfe) {
        ErrorResponse errorResponse = ErrorResponse.generalError(101, bnfe.getMessage());
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
