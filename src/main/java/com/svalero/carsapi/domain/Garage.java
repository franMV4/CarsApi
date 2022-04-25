package com.svalero.carsapi.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "garages")
public class Garage {

    @Id
    private String id;
    @Field
    @NotNull
    private String street;
    @Field
    private String city;
    @Field(name = "postal_code")
    private String postalCode;
    @Field
    @NotNull
    private String owner;
    @Field
    @Positive
    private int capacity;

    @OneToMany(mappedBy = "garage")
    private List<Reparation> reparations;
}
