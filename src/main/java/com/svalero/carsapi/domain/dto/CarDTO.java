package com.svalero.carsapi.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CarDTO {


    @NotNull
    private String brand;
    @NotNull
    private String model;
    private String color;
    @Positive
    private int horsePower;
    @Positive
    private int seats;



    private long user;

}
