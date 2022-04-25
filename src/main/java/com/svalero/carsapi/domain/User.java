package com.svalero.carsapi.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "users")
public class User {

    @Id
    private String id;
    @Field
    @NotNull
    @Pattern(regexp = "[0-9]{8}[A-Z]")
    private String dni;
    @Field
    private String name;
    @Field
    private String surname;
    @Field
    @NotNull
    private String phone;
    @Field
    private String adress;

    @OneToMany(mappedBy = "user")
    private List<Car> cars;




}
