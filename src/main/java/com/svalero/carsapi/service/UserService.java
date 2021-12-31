package com.svalero.carsapi.service;

import com.svalero.carsapi.domain.Car;
import com.svalero.carsapi.domain.User;
import com.svalero.carsapi.domain.dto.CarDTO;
import com.svalero.carsapi.domain.dto.UserDTO;
import com.svalero.carsapi.exception.CarNotFoundException;
import com.svalero.carsapi.exception.UserNotFoundException;

import java.util.List;


public interface UserService {
    List<User> findAllUsers();

    User addUser(UserDTO userDto) throws UserNotFoundException;
    User deleteUser(long id) throws UserNotFoundException;
    User modifyUser(long id, User user) throws UserNotFoundException ;
    User patchUser(long id, String name) throws UserNotFoundException ;

}