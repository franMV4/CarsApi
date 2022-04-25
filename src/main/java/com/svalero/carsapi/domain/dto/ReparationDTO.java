package com.svalero.carsapi.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;


@Data
@NoArgsConstructor
public class ReparationDTO {

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate pickUpDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfDelivery;
    @Positive
    private int cost;
    @NotNull
    private String repairedPart;
    @NotNull
    private boolean delivered;
    private int numeroMecanicos;


    private long car;
    private long garage;


}
