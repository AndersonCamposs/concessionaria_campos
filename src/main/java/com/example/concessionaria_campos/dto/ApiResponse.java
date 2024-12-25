package com.example.concessionaria_campos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Schema(description = "Detalhes de uma resposta personalizada")
@Getter
@Setter
public class ApiResponse {
    private String message;
    private LocalDateTime timestamp;

    public ApiResponse(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}
