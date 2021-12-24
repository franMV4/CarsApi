package com.svalero.carsapi.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CarDTO {


    private String brand;
    private String model;
    private String color;
    private int horsePower;
    private int seats;



    private long user;

}
