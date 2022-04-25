package com.svalero.carsapi.service;

import com.svalero.carsapi.domain.Car;
import com.svalero.carsapi.domain.Reparation;
import com.svalero.carsapi.domain.User;
import com.svalero.carsapi.domain.dto.CarDTO;
import com.svalero.carsapi.domain.dto.ReparationDTO;
import com.svalero.carsapi.exception.CarNotFoundException;
import com.svalero.carsapi.exception.ReparationNotFoundException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


public interface ReparationService {
    Flux<Reparation> findAllReparations();
    Flux<Reparation> findReparations(Car car, int cost);
    Flux<Reparation> findReparations(Car car);




    Mono<Reparation> addReparation(ReparationDTO reparationDto) throws ReparationNotFoundException;
    Mono<Reparation> deleteReparation(long id) throws ReparationNotFoundException;
    Mono<Reparation> modifyReparation(long id, Reparation reparation) throws ReparationNotFoundException ;
    Mono<Reparation> patchReparation(long id, String repairedPart) throws ReparationNotFoundException;


}