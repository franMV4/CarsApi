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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReparationController {
    private final Logger logger = LoggerFactory.getLogger(ReparationController.class);

    @Autowired
    private ReparationService reparationService;




    @GetMapping("/reparations")
    public List<Reparation> getReparations() {
        logger.info("Start getReparations");
        List<Reparation> reparations;
        reparations = reparationService.findAllReparations();
        logger.info("End getReparations");
        return reparations;
    }



    @PostMapping("/reparations")
    public Reparation addReparation(@RequestBody ReparationDTO reparationDto) throws ReparationNotFoundException {
        logger.info("Start addReparation");
        return reparationService.addReparation(reparationDto);
    }

    @DeleteMapping("/reparation/{id}")
    public Reparation removeReparation(@PathVariable long id) throws ReparationNotFoundException {
        logger.info("Start removeReparation");
        Reparation reparation = reparationService.deleteReparation(id);
        logger.info("End removeReparation");
        return reparation;
    }

    @PutMapping("/reparation/{id}")
    public Reparation modifyReparation(@RequestBody Reparation reparation, @PathVariable long id) throws ReparationNotFoundException {
        logger.info("Start modifyReparation"+ id);
        Reparation newReparation = reparationService.modifyReparation(id, reparation);
        logger.info("End removeReparation" + id);
        return newReparation;
    }

    @PatchMapping("/reparation/{id}")
    public Reparation patchReparation(@PathVariable long id, @RequestBody String repairedPart) throws ReparationNotFoundException {
        logger.info("Start patchReparation" + id);

        Reparation reparation = reparationService.patchReparation(id, repairedPart);
        logger.info("End patchReparation" + id);
        return reparation;
    }
}
