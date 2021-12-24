package com.svalero.carsapi.controller;


import com.svalero.carsapi.domain.Car;
import com.svalero.carsapi.domain.Reparation;

import com.svalero.carsapi.domain.User;
import com.svalero.carsapi.domain.dto.CarDTO;
import com.svalero.carsapi.domain.dto.ReparationDTO;
import com.svalero.carsapi.exception.CarNotFoundException;
import com.svalero.carsapi.exception.ReparationNotFoundException;
import com.svalero.carsapi.service.CarService;
import com.svalero.carsapi.service.ReparationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReparationController {

    @Autowired
    private ReparationService reparationService;
    @Autowired
    private CarService carService;



    @GetMapping("/reparations")
    public List<Reparation> getReparations() {
        List<Reparation> reparations;
        reparations = reparationService.findAllReparations();
        return reparations;
    }



    @PostMapping("/reparations")
    public Reparation addReparation(@RequestBody ReparationDTO reparationDto) throws ReparationNotFoundException {
        return reparationService.addReparation(reparationDto);
    }

    @DeleteMapping("/reparation/{id}")
    public Reparation removeReparation(@PathVariable long id) throws ReparationNotFoundException {
        Reparation reparation = reparationService.deleteReparation(id);
        return reparation;
    }

    @PutMapping("/reparation/{id}")
    public Reparation modifyReparation(@RequestBody Reparation reparation, @PathVariable long id) throws ReparationNotFoundException {
        Reparation newReparation = reparationService.modifyReparation(id, reparation);
        return newReparation;
    }

    @PatchMapping("/reparation/{id}")
    public Reparation patchReparation(@PathVariable long id, @RequestBody String repairedPart) throws ReparationNotFoundException {
        // logger.info("Start PatchCar " + id);
        Reparation reparation = reparationService.patchReparation(id, repairedPart);
        //  logger.info("End patchCar " + id);
        return reparation;
    }
}
