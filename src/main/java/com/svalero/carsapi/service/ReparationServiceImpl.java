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
    public List<Reparation> findAllReparations() {
        return reparationRepository.findAll();
    }

    @Override
    public List<Reparation> findReparations(Car car, int cost) {
        return reparationRepository.findByCarAndCost(car, cost);
    }

    @Override
    public List<Reparation> findReparations(Car car) {
        return reparationRepository.findByCar(car);
    }


    @Override
    public Reparation addReparation(ReparationDTO reparationDto) throws ReparationNotFoundException {
        Car car = carRepository.findById(reparationDto.getCar())
                .orElseThrow(ReparationNotFoundException::new);
        Garage garage = garageRepository.findById(reparationDto.getGarage())
                .orElseThrow(ReparationNotFoundException::new);

        ModelMapper mapper = new ModelMapper();
        Reparation reparation = mapper.map(reparationDto, Reparation.class);

        reparation.setCar(car);
        reparation.setGarage(garage);
        return reparationRepository.save(reparation);}

    @Override
    public Reparation deleteReparation(long id) throws ReparationNotFoundException {
        Reparation reparation = reparationRepository.findById(id)
                .orElseThrow(ReparationNotFoundException::new);
        reparationRepository.delete(reparation);
        return reparation;
    }

    @Override
    public Reparation modifyReparation(long id, Reparation newReparation) throws ReparationNotFoundException {
        Reparation reparation = reparationRepository.findById(id)
                .orElseThrow(ReparationNotFoundException::new);
        reparation.setRepairedPart(newReparation.getRepairedPart());

        return reparationRepository.save(reparation);
    }

    @Override
    public Reparation patchReparation(long id, String repairedPart) throws ReparationNotFoundException {
        Reparation reparation = reparationRepository.findById(id)
                .orElseThrow(ReparationNotFoundException::new);
        reparation.setRepairedPart(repairedPart);
        return reparationRepository.save(reparation);
    }

}
