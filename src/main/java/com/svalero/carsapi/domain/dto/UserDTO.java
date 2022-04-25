package com.svalero.carsapi.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UserDTO {

    @NotNull
    @Pattern(regexp = "[0-9]{8}[A-Z]")
    private String dni;
    private String name;
    private String surname;
    @NotNull
    private String phone;
    private String adress;
}