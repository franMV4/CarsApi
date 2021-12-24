package com.svalero.carsapi.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UserDTO {

    private String dni;
    private String name;
    private String surname;
    private String phone;
    private String adress;
}