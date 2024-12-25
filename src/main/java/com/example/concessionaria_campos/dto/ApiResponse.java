package com.example.concessionaria_campos.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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
