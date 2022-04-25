package com.svalero.carsapi.service;


import com.svalero.carsapi.domain.Garage;
import com.svalero.carsapi.domain.User;
import com.svalero.carsapi.domain.dto.GarageDTO;
import com.svalero.carsapi.domain.dto.UserDTO;
import com.svalero.carsapi.exception.CarNotFoundException;
import com.svalero.carsapi.exception.GarageNotFoundException;
import com.svalero.carsapi.repository.GarageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class GarageServiceImpl implements GarageService {

    @Autowired
    private GarageRepository garageRepository;

    @Override
    public Flux<Garage> findAllGarages() {
        return (Flux<Garage>) garageRepository.findAll();
    }

    @Override
    public Mono<Garage> addGarage (GarageDTO garageDto)  {
        ModelMapper mapper = new ModelMapper();
        Garage garage = mapper.map(garageDto, Garage.class);
        return garageRepository.save(garage);}

    @Override
    public Mono<Garage> deleteGarage(long id) throws GarageNotFoundException {
        Mono<Garage> garage = garageRepository.findById(id)
                .onErrorReturn(new Garage());
        garageRepository.delete(garage.block());
        return garage;
    }

    @Override
    public Mono<Garage> modifyGarage(long id, Garage newGarage) throws GarageNotFoundException {
        Mono<Garage> garage = garageRepository.findById(id)
                .onErrorReturn(new Garage());
        garage.block().setPostalCode(newGarage.getPostalCode());

        return garageRepository.save(garage.block());
    }

    @Override
    public Mono<Garage> patchGarage(long id, String postalCode) throws GarageNotFoundException {
        Mono<Garage> garage = garageRepository.findById(id)
                .onErrorReturn(new Garage());
        garage.block().setPostalCode(postalCode);
        return garageRepository.save(garage.block());
    }


}