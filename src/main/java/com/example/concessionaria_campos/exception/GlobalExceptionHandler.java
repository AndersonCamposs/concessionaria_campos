package com.example.concessionaria_campos.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException e) {
        List<String> errors = new ArrayList<>();
        e.getAllErrors()
                .forEach(objectError -> errors.addLast(objectError.getDefaultMessage()));

        return ResponseEntity
                .status(e.getStatusCode())
                .body(new ErrorDetails(e.getStatusCode().value(), "Erro na validação dos dados", errors));
    }
}
