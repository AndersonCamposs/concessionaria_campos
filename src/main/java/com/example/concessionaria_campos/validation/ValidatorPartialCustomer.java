package com.example.concessionaria_campos.validation;

import com.example.concessionaria_campos.dto.CustomerDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidatorPartialCustomer implements ConstraintValidator<ValidPartialCustomer, CustomerDTO> {


    @Override
    public boolean isValid(CustomerDTO customerDTO, ConstraintValidatorContext constraintValidatorContext) {
        return customerDTO != null && customerDTO.getId() != null;
    }
}
