package com.svalero.carsapi.domain.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class GarageDTO {

    private String street;
    private String city;
    private String postalCode;
    private String owner;
    private int capacity;




}
