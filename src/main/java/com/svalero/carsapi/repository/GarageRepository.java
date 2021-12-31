package com.svalero.carsapi.repository;



import com.svalero.carsapi.domain.Garage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GarageRepository extends CrudRepository<Garage, Long> {


    List<Garage> findAll();



}
