package com.svalero.carsapi.service;

import com.svalero.carsapi.domain.Car;
import com.svalero.carsapi.domain.Reparation;
import com.svalero.carsapi.domain.User;
import com.svalero.carsapi.domain.dto.CarDTO;
import com.svalero.carsapi.domain.dto.UserDTO;
import com.svalero.carsapi.exception.CarNotFoundException;
import com.svalero.carsapi.exception.UserNotFoundException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


public interface UserService {
    Flux<User> findAllUsers();

    Mono<User> addUser(UserDTO userDto) throws UserNotFoundException;
    Mono<User> deleteUser(long id) throws UserNotFoundException;
    Mono<User> modifyUser(long id, User user) throws UserNotFoundException ;
    Mono<User> patchUser(long id, String name) throws UserNotFoundException ;

}