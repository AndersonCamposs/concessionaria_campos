package com.example.concessionaria_campos.exception;

public class RegisterAlreadyExists extends RuntimeException {
    public RegisterAlreadyExists(String message) {
        super(message);
    }
}
