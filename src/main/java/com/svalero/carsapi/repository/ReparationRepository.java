package com.svalero.carsapi.repository;



import com.svalero.carsapi.domain.Car;
import com.svalero.carsapi.domain.Reparation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;

@Repository
public interface ReparationRepository  extends ReactiveMongoRepository<Reparation, Long> {


    Flux<Reparation> findAll();
    Flux<Reparation> findByCar(Car car);
    Flux<Reparation> findByCarAndCost(Car car, int cost);



}
