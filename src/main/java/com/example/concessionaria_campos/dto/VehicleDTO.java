package com.example.concessionaria_campos.dto;

import com.example.concessionaria_campos.entity.Brand;
import com.example.concessionaria_campos.entity.Category;
import com.example.concessionaria_campos.entity.Photo;
import com.example.concessionaria_campos.enums.TransmissionType;
import com.example.concessionaria_campos.enums.VehicleStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class VehicleDTO {

    private Long id;

    @NotBlank(message = "O campo 'modelo' é obrigatório.", groups = {Create.class, Update.class})
    private String model;

    @NotBlank(message = "O campo 'número do chassi' é obrigatório.", groups = {Create.class, Update.class})
    private String chassisNumber;

    @NotBlank(message = "O campo 'placa' é obrigatório.", groups = {Create.class, Update.class})
    private String plate;

    @NotNull(message = "O campo 'marca' é obrigatório.", groups = {Create.class, Update.class})
    private Brand brand;

    @NotNull(message = "O campo 'ano de fabricação' é obrigatório.", groups = {Create.class, Update.class})
    private Integer year;

    @NotNull(message = "O campo 'categoria' é obrigatório.", groups = {Create.class, Update.class})
    private Category category;

    @NotBlank(message = "O campo 'tipo de transmissão' é obrigatório.", groups = {Create.class, Update.class})
    private TransmissionType transmissionType;

    private VehicleStatus status;

    @JsonIgnore
    private Set<Photo> photos;
}
