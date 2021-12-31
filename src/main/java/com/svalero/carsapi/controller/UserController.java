package com.svalero.carsapi.controller;

import com.svalero.carsapi.domain.Car;
import com.svalero.carsapi.domain.User;
import com.svalero.carsapi.domain.dto.CarDTO;
import com.svalero.carsapi.domain.dto.UserDTO;
import com.svalero.carsapi.exception.CarNotFoundException;
import com.svalero.carsapi.exception.UserNotFoundException;
import com.svalero.carsapi.service.CarService;
import com.svalero.carsapi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;



    @GetMapping("/users")
    public List<User> getUsers() {
        logger.info("Start getUsers");
        List<User> users;
            users = userService.findAllUsers();
        logger.info("End getUsers");
        return users;
    }

    @PostMapping("/users")
    public User addUser(@RequestBody UserDTO userDto) throws UserNotFoundException {
        logger.info("Start addUser");

        return userService.addUser(userDto);

    }

    @DeleteMapping("/user/{id}")
    public User removeUser(@PathVariable long id) throws UserNotFoundException {
        logger.info("Start removeUser" + id);

        User user = userService.deleteUser(id);
        logger.info("End removeUser" + id);
        return user;
    }

    @PutMapping("/user/{id}")
    public User modifyUser(@RequestBody User user, @PathVariable long id) throws UserNotFoundException {
        logger.info("Start modifyUser" + id);
        User newUser = userService.modifyUser(id, user);
        logger.info("End modifyUser" + id);
        return newUser;
    }

    @PatchMapping("/user/{id}")
    public User patchUser(@PathVariable long id, @RequestBody String name) throws UserNotFoundException {
        logger.info("Start patchUser" + id);
        User user = userService.patchUser(id, name);
        logger.info("End patchUser" + id);
        return user;
    }
}
