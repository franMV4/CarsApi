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

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User addUser (UserDTO userDto)  {
    ModelMapper mapper = new ModelMapper();
        User user = mapper.map(userDto, User.class);
        return userRepository.save(user);}

    @Override
    public User deleteUser(long id) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
        return user;
    }

    @Override
    public User modifyUser(long id, User newUser) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        user.setName(newUser.getName());

        return userRepository.save(user);
    }

    @Override
    public User patchUser(long id, String name) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        user.setName(name);
        return userRepository.save(user);
    }

}