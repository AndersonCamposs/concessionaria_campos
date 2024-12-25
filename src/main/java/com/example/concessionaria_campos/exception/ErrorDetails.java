package com.example.concessionaria_campos.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ErrorDetails {
    private int status;
    private String message;
    private List<String> errors;

    public ErrorDetails(int status, String message, List<String> errors) {
        this.message = message;
        this.errors = errors;
        this.status = status;
    }
}
