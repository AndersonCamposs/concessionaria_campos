package com.example.concessionaria_campos.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Schema(description = "Detalhes do cliente")
@Getter
@Setter
public class ClienteDTO {
    private Long id;

    private String nome;

    private String cpf;

    private LocalDate dataNascimento;
}
