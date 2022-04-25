package com.svalero.carsapi.service;


import com.svalero.carsapi.domain.Car;
import com.svalero.carsapi.domain.User;
import com.svalero.carsapi.domain.dto.UserDTO;
import com.svalero.carsapi.exception.CarNotFoundException;
import com.svalero.carsapi.exception.UserNotFoundException;
import com.svalero.carsapi.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public Flux<User> findAllUsers() {
        return (Flux<User>) userRepository.findAll();
    }

    @Override
    public Mono<User> addUser (UserDTO userDto)  {
    ModelMapper mapper = new ModelMapper();
        User user = mapper.map(userDto, User.class);
        return userRepository.save(user);}

    @Override
    public Mono<User> deleteUser(long id) throws UserNotFoundException {
        Mono<User> user = userRepository.findById(id)
                .onErrorReturn(new User());
        userRepository.delete(user.block());
        return user;
    }

    @Override
    public Mono<User> modifyUser(long id, User newUser) throws UserNotFoundException {
        Mono<User> user = userRepository.findById(id)
                .onErrorReturn(new User());
        user.block().setName(newUser.getName());

        return userRepository.save(user.block());
    }

    @Override
    public Mono<User> patchUser(long id, String name) throws UserNotFoundException {
        Mono<User> user = userRepository.findById(id)
                .onErrorReturn(new User());
        user.block().setName(name);
        return userRepository.save(user.block());
    }

}