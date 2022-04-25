package com.svalero.carsapi.controller;


import com.svalero.carsapi.domain.Car;
import com.svalero.carsapi.domain.Reparation;

import com.svalero.carsapi.domain.User;
import com.svalero.carsapi.domain.dto.CarDTO;
import com.svalero.carsapi.domain.dto.ReparationDTO;
import com.svalero.carsapi.exception.CarNotFoundException;
import com.svalero.carsapi.exception.ErrorResponse;
import com.svalero.carsapi.exception.ReparationNotFoundException;
import com.svalero.carsapi.service.CarService;
import com.svalero.carsapi.service.ReparationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.config.RepositoryNameSpaceHandler;
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
public class ReparationController {

    private final Logger logger = LoggerFactory.getLogger(ReparationController.class);

    @Autowired
    private ReparationService reparationService;
    @Autowired
    private CarService carService;



    @GetMapping("/reparations")
    public ResponseEntity<Flux<Reparation>> getReparations() {
        Flux<Reparation> reparations;
        reparations = reparationService.findAllReparations();
        return ResponseEntity.ok(reparations);
    }



    @PostMapping("/reparations")
    public ResponseEntity<?> addReparation(@Valid @RequestBody ReparationDTO reparationDto) throws ReparationNotFoundException {
        Mono<Reparation> newReparation = reparationService.addReparation(reparationDto);
        return ResponseEntity.ok(newReparation.block());
    }

    @DeleteMapping("/reparation/{id}")
    public ResponseEntity<Mono<Reparation>> removeReparation(@PathVariable long id) throws ReparationNotFoundException {
        Mono<Reparation> reparation = reparationService.deleteReparation(id);
        return ResponseEntity.ok(reparation);
    }

    @PutMapping("/reparation/{id}")
    public ResponseEntity<Mono<Reparation>> modifyReparation(@RequestBody Reparation reparation, @PathVariable long id) throws ReparationNotFoundException {
        Mono<Reparation> newReparation = reparationService.modifyReparation(id, reparation);
        return ResponseEntity.ok(newReparation);
    }

    @PatchMapping("/reparation/{id}")
    public ResponseEntity<Mono<Reparation>> patchReparation(@PathVariable long id, @RequestBody String repairedPart) throws ReparationNotFoundException {
        // logger.info("Start PatchCar " + id);
        Mono<Reparation> reparation = reparationService.patchReparation(id, repairedPart);
        //  logger.info("End patchCar " + id);
        return ResponseEntity.ok(reparation) ;
    }

    @ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleGarageNotFoundException(ReparationNotFoundException bnfe) {
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
