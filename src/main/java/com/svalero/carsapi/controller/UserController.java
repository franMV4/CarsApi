package com.svalero.carsapi.controller;

import com.svalero.carsapi.domain.Car;
import com.svalero.carsapi.domain.User;
import com.svalero.carsapi.domain.dto.CarDTO;
import com.svalero.carsapi.domain.dto.UserDTO;
import com.svalero.carsapi.exception.CarNotFoundException;
import com.svalero.carsapi.exception.UserNotFoundException;
import com.svalero.carsapi.service.CarService;
import com.svalero.carsapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;



    @GetMapping("/users")
    public List<User> getUsers() {
        List<User> users;
            users = userService.findAllUsers();
        return users;
    }

    @PostMapping("/users")
    public User addUser(@RequestBody UserDTO userDto) throws UserNotFoundException {
        return userService.addUser(userDto);

    }

    @DeleteMapping("/user/{id}")
    public User removeUser(@PathVariable long id) throws UserNotFoundException {
        User user = userService.deleteUser(id);
        return user;
    }

    @PutMapping("/user/{id}")
    public User modifyUser(@RequestBody User user, @PathVariable long id) throws UserNotFoundException {
        User newUser = userService.modifyUser(id, user);
        return newUser;
    }

    @PatchMapping("/user/{id}")
    public User patchUser(@PathVariable long id, @RequestBody String name) throws UserNotFoundException {
        // logger.info("Start PatchCar " + id);
        User user = userService.patchUser(id, name);
        //  logger.info("End patchCar " + id);
        return user;
    }
}
