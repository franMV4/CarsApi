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

import java.util.List;

@Service
public class GarageServiceImpl implements GarageService {

    @Autowired
    private GarageRepository garageRepository;

    @Override
    public List<Garage> findAllGarages() {
        return (List<Garage>) garageRepository.findAll();
    }

    @Override
    public Garage addGarage (GarageDTO garageDto)  {
        ModelMapper mapper = new ModelMapper();
        Garage garage = mapper.map(garageDto, Garage.class);
        return garageRepository.save(garage);}

    @Override
    public Garage deleteGarage(long id) throws GarageNotFoundException {
        Garage garage = garageRepository.findById(id)
                .orElseThrow(GarageNotFoundException::new);
        garageRepository.delete(garage);
        return garage;
    }

    @Override
    public Garage modifyGarage(long id, Garage newGarage) throws GarageNotFoundException {
        Garage garage = garageRepository.findById(id)
                .orElseThrow(GarageNotFoundException::new);
        garage.setPostalCode(newGarage.getPostalCode());

        return garageRepository.save(garage);
    }

    @Override
    public Garage patchGarage(long id, String postalCode) throws GarageNotFoundException {
        Garage garage = garageRepository.findById(id)
                .orElseThrow(GarageNotFoundException::new);
        garage.setPostalCode(postalCode);
        return garageRepository.save(garage);
    }


}