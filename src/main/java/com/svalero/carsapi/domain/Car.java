package com.svalero.carsapi.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.awt.image.ImageProducer;
import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "cars")
public class Car {

    @Id
    private String id;
    @Field
    @NotNull
    private String brand;
    @Field
    @NotNull
    private String model;
    @Field
    private String color;
    @Field(name = "horse_power")
    @Positive
    private int horsePower;
    @Field
    @Positive
    private int seats;

    @OneToMany(mappedBy = "car")
    private List<Reparation> reparations;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    @JsonBackReference (value = "user-car")
    private User user;



}