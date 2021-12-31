package com.svalero.carsapi.repository;



import com.svalero.carsapi.domain.Car;
import com.svalero.carsapi.domain.Reparation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReparationRepository  extends CrudRepository<Reparation, Long> {


    List<Reparation> findAll();
    List<Reparation> findByCar(Car car);
    List<Reparation> findByCarAndCost(Car car, int cost);



}
