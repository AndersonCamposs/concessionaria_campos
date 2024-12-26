package com.example.concessionaria_campos.dto;


import com.example.concessionaria_campos.entity.Cliente;
import com.example.concessionaria_campos.validation.ValidCPF;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@Schema(description = "Detalhes do cliente")
@Getter
@Setter
public class ClienteDTO extends RepresentationModel<ClienteDTO> {
    private Long id;

    @NotBlank(message = "O campo 'nome' é obrigatório.", groups = {Create.class, Update.class})
    private String nome;

    @NotNull(message =  "O campo 'cpf' é obrigatório", groups = Create.class)
    @Pattern(regexp = "[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{2}", message = "Padrão inválido de CPF.", groups = Create.class)
    @ValidCPF(groups = { Create.class, Update.class })
    private String cpf;

    @Email(message = "E-mail inválido.")
    private String email;

    @NotNull(message = "O campo 'dataNascimento' é obrigatório.", groups = Create.class)
    private LocalDate dataNascimento;
}
