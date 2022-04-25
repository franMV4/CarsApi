package com.svalero.carsapi.domain.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class GarageDTO {

    @NotNull
    private String street;
    private String city;
    private String postalCode;
    @NotNull
    private String owner;
    @Positive
    private int capacity;




}
