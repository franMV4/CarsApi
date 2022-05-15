package com.svalero.carsapi.service;


import com.svalero.carsapi.domain.Car;
import com.svalero.carsapi.domain.Garage;
import com.svalero.carsapi.domain.Reparation;
import com.svalero.carsapi.domain.User;
import com.svalero.carsapi.domain.dto.CarDTO;
import com.svalero.carsapi.domain.dto.ReparationDTO;
import com.svalero.carsapi.exception.CarNotFoundException;
import com.svalero.carsapi.exception.ReparationNotFoundException;
import com.svalero.carsapi.repository.CarRepository;
import com.svalero.carsapi.repository.GarageRepository;
import com.svalero.carsapi.repository.ReparationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ReparationServiceImpl implements ReparationService{

    @Autowired
    private ReparationRepository reparationRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private GarageRepository garageRepository;



    @Override
    public Flux<Reparation> findAllReparations() {
        return reparationRepository.findAll();
    }

    @Override
    public Flux<Reparation> findReparations(Car car, int cost) {
        return reparationRepository.findByCarAndCost(car, cost);
    }

    @Override
    public Flux<Reparation> findReparations(Car car) {
        return reparationRepository.findByCar(car);
    }


    @Override
    public Mono<Reparation> addReparation(ReparationDTO reparationDto) throws ReparationNotFoundException {
        Mono<Car> car = carRepository.findById(reparationDto.getCar())
                .onErrorReturn(new Car());
        Mono<Garage> garage = garageRepository.findById(reparationDto.getGarage())
                .onErrorReturn(new Garage());

        ModelMapper mapper = new ModelMapper();
        Reparation reparation = mapper.map(reparationDto, Reparation.class);

        reparation.setCar(car.block());
        reparation.setGarage(garage.block());
        return reparationRepository.save(reparation);}

    @Override
    public Mono<Reparation> deleteReparation(long id) throws ReparationNotFoundException {
        Mono<Reparation> reparation = reparationRepository.findById(id)
                .onErrorReturn(new Reparation());
        reparationRepository.delete(reparation.block());
        return reparation;
    }

    @Override
    public Mono<Reparation> modifyReparation(long id, Reparation newReparation) throws ReparationNotFoundException {
        Mono<Reparation> reparation = reparationRepository.findById(id)
                .onErrorReturn(new Reparation());

        reparation.block().setCost(newReparation.getCost());
        reparation.block().setDelivered(newReparation.isDelivered());
        reparation.block().setNumeroMecanicos(newReparation.getNumeroMecanicos());
        reparation.block().setPickUpDate(newReparation.getPickUpDate());
        reparation.block().setDateOfDelivery(newReparation.getDateOfDelivery());
        reparation.block().setRepairedPart(newReparation.getRepairedPart());


        return reparationRepository.save(reparation.block());
    }

    @Override
    public Mono<Reparation> patchReparation(long id, String repairedPart) throws ReparationNotFoundException {
        Mono<Reparation> reparation = reparationRepository.findById(id)
                .onErrorReturn(new Reparation());
        reparation.block().setRepairedPart(repairedPart);
        return reparationRepository.save(reparation.block());
    }

}
