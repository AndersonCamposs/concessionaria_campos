package com.example.concessionaria_campos.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class BrandDTO extends RepresentationModel<BrandDTO> {

    private Long id;

    @NotBlank(message = "O campo 'nome' é obrigatório", groups = {Create.class, Update.class})
    private String name;

    private String image;
}
