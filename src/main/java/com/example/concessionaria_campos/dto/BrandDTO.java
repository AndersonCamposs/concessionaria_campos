package com.example.concessionaria_campos.dto;

import com.example.concessionaria_campos.entity.Vehicle;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.Set;

@Getter
@Setter
public class BrandDTO extends RepresentationModel<BrandDTO> {

    private Long id;

    @NotBlank(message = "O campo 'nome' é obrigatório", groups = {Create.class, Update.class})
    private String name;

    private String image;

    @JsonIgnore
    private Set<Vehicle> vehicles;
}
