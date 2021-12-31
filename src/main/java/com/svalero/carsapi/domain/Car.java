package com.svalero.carsapi.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.awt.image.ImageProducer;
import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String brand;
    @Column
    private String model;
    @Column
    private String color;
    @Column(name = "horse_power")
    private int horsePower;
    @Column
    private int seats;

    @OneToMany(mappedBy = "car")
    private List<Reparation> reparations;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    @JsonBackReference (value = "user-car")
    private User user;



}