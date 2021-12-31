package com.svalero.carsapi.controller;

import com.svalero.carsapi.domain.Car;
import com.svalero.carsapi.domain.Garage;
import com.svalero.carsapi.domain.User;
import com.svalero.carsapi.domain.dto.GarageDTO;
import com.svalero.carsapi.domain.dto.UserDTO;
import com.svalero.carsapi.exception.CarNotFoundException;
import com.svalero.carsapi.exception.GarageNotFoundException;
import com.svalero.carsapi.service.CarService;
import com.svalero.carsapi.service.GarageService;
import com.svalero.carsapi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GarageController {
    private final Logger logger = LoggerFactory.getLogger(GarageController.class);

    @Autowired
    private GarageService garageService;



    @GetMapping("/garages")
    public List<Garage> getGarage() {
        logger.info("Start getGarages");
        List<Garage> garages;


        garages = garageService.findAllGarages();
        logger.info("End getGarages");


        return garages;
    }

    @DeleteMapping("/garage/{id}")
    public Garage removeGarage(@PathVariable long id) throws GarageNotFoundException {
        logger.info("Start deleteGarage" + id);
        Garage garage = garageService.deleteGarage(id);
        logger.info("End deleteGarage"  + id);
        return garage;
    }
    @PostMapping("/garages")
    public Garage addGarage(@RequestBody GarageDTO garageDto) throws GarageNotFoundException {
        logger.info("Start addGarage");
        return garageService.addGarage(garageDto);

    }

    @PutMapping("/garage/{id}")
    public Garage modifyGarage(@RequestBody Garage garage, @PathVariable long id) throws GarageNotFoundException {
        logger.info("Start modifyGarage" + id);
        Garage newGarage = garageService.modifyGarage(id, garage);
        logger.info("End modifyGarage" + id);
        return newGarage;
    }

    @PatchMapping("/garage/{id}")
    public Garage patchGarage(@PathVariable long id, @RequestBody String postalCode) throws GarageNotFoundException {
        logger.info("Start patchGarage" + id);
        Garage garage = garageService.patchGarage(id, postalCode);
        logger.info("End patchGarage" + id);
        return garage;
    }
}
