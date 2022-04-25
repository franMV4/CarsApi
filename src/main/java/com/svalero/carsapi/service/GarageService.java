package com.svalero.carsapi.service;

import com.svalero.carsapi.domain.Car;
import com.svalero.carsapi.domain.Garage;
import com.svalero.carsapi.domain.dto.GarageDTO;
import com.svalero.carsapi.exception.CarNotFoundException;
import com.svalero.carsapi.exception.GarageNotFoundException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


public interface GarageService {
    Flux<Garage> findAllGarages();

    Mono<Garage> addGarage(GarageDTO garageDto) throws GarageNotFoundException;
    Mono<Garage> deleteGarage(long id) throws GarageNotFoundException;
    Mono<Garage> modifyGarage(long id, Garage garage) throws GarageNotFoundException ;
    Mono<Garage> patchGarage(long id, String postalCode) throws GarageNotFoundException;

}