package com.svalero.carsapi.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "reparations")
public class Reparation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "repaired_part")
    private String repairedPart;
    @Column
    private int cost;
    @Column
    private String problem;
    @Column
    private boolean delivered;
    @Column(name = "numero_mecanicos")
    private int numeroMecanicos;
    @Column(name = "pick_up_date")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate pickUpDate;
    @Column(name = "date_of_delivery")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfDelivery;

    @ManyToOne()
    @JoinColumn(name = "car_id")
    @JsonBackReference
    private Car car;

    @ManyToOne()
    @JoinColumn(name = "garage_id")
    @JsonBackReference
    private Garage garage;


}
