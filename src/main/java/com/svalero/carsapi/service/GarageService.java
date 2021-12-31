package com.svalero.carsapi.service;

import com.svalero.carsapi.domain.Garage;
import com.svalero.carsapi.domain.dto.GarageDTO;
import com.svalero.carsapi.exception.CarNotFoundException;
import com.svalero.carsapi.exception.GarageNotFoundException;

import java.util.List;


public interface GarageService {
    List<Garage> findAllGarages();

    Garage addGarage(GarageDTO garageDto) throws GarageNotFoundException;
    Garage deleteGarage(long id) throws GarageNotFoundException;
    Garage modifyGarage(long id, Garage garage) throws GarageNotFoundException ;
    Garage patchGarage(long id, String postalCode) throws GarageNotFoundException;

}