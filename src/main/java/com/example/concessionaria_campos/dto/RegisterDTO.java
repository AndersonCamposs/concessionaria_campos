package com.example.concessionaria_campos.dto;

import com.example.concessionaria_campos.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class RegisterDTO {
    @NotBlank(message = "O login do usuário é obrigatório.", groups = { Create.class })
    private String login;

    @NotBlank(message = "A senha do usuário é obrigatório.", groups = { Create.class })
    private String password;

    @NotNull(message = "A permissão do usuário é obrigatória", groups = { Create.class })
    private UserRole role;
}
