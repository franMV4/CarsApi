package com.svalero.carsapi.controller;

import com.svalero.carsapi.domain.Car;
import com.svalero.carsapi.domain.User;
import com.svalero.carsapi.domain.dto.CarDTO;
import com.svalero.carsapi.domain.dto.UserDTO;
import com.svalero.carsapi.exception.CarNotFoundException;
import com.svalero.carsapi.exception.ErrorResponse;
import com.svalero.carsapi.exception.UserNotFoundException;
import com.svalero.carsapi.service.CarService;
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
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;



    @GetMapping("/users")
    public ResponseEntity<Flux<User>> getUsers() {
        Flux<User> users;
            users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/users")
    public ResponseEntity<?> addUser(@Valid @RequestBody UserDTO userDto) throws UserNotFoundException {
        Mono<User> newUser = userService.addUser(userDto);
        return ResponseEntity.ok(newUser.block());

    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Mono<User>> removeUser(@PathVariable long id) throws UserNotFoundException {
        Mono<User> user = userService.deleteUser(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<Mono<User>> modifyUser(@RequestBody User user, @PathVariable long id) throws UserNotFoundException {
        Mono<User> newUser = userService.modifyUser(id, user);
        return ResponseEntity.ok(newUser);
    }

    @PatchMapping("/user/{id}")
    public ResponseEntity<Mono<User>> patchUser(@PathVariable long id, @RequestBody String name) throws UserNotFoundException {
        // logger.info("Start PatchCar " + id);
        Mono<User> user = userService.patchUser(id, name);
        //  logger.info("End patchCar " + id);
        return ResponseEntity.ok(user);
    }


    @ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleGarageNotFoundException(UserNotFoundException bnfe) {
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
