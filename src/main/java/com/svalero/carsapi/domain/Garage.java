package com.svalero.carsapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "garages")
public class Garage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String street;
    @Column
    private String city;
    @Column(name = "postal_code")
    private String postalCode;
    @Column
    private String owner;
    @Column
    private int capacity;

    @OneToMany(mappedBy = "garage")
    private List<Reparation> reparations;
}
