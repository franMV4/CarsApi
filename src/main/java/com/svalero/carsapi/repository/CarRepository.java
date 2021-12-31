package com.svalero.carsapi.repository;

import com.svalero.carsapi.domain.Car;
import com.svalero.carsapi.domain.Reparation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {


    List<Car> findAll();
















}
