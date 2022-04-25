package com.svalero.carsapi.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "reparations")
public class Reparation {

    @Id
    private String id;
    @Field(name = "repaired_part")
    @NotNull
    private String repairedPart;
    @Field
    @Positive
    private int cost;
    @Field
    @NotNull
    private boolean delivered;
    @Field(name = "numero_mecanicos")
    private int numeroMecanicos;
    @Field(name = "pick_up_date")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate pickUpDate;
    @Field(name = "date_of_delivery")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfDelivery;

    @ManyToOne()
    @JoinColumn(name = "car_id")
    @JsonBackReference(value = "car-reparation")
    private Car car;

    @ManyToOne()
    @JoinColumn(name = "garage_id")
    @JsonBackReference(value = "garage-reparation")
    private Garage garage;


}
