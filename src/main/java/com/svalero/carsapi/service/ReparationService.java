package com.svalero.carsapi.service;

import com.svalero.carsapi.domain.Car;
import com.svalero.carsapi.domain.Reparation;
import com.svalero.carsapi.domain.dto.CarDTO;
import com.svalero.carsapi.domain.dto.ReparationDTO;
import com.svalero.carsapi.exception.CarNotFoundException;
import com.svalero.carsapi.exception.ReparationNotFoundException;

import java.util.List;


public interface ReparationService {
    List<Reparation> findAllReparations();
    List<Reparation> findReparations(Car car, int cost);
    List<Reparation> findReparations(Car car);




    Reparation addReparation(ReparationDTO reparationDto) throws ReparationNotFoundException;
    Reparation deleteReparation(long id) throws ReparationNotFoundException;
    Reparation modifyReparation(long id, Reparation reparation) throws ReparationNotFoundException ;
    Reparation patchReparation(long id, String repairedPart) throws ReparationNotFoundException;


}