package com.example.concessionaria_campos.validation;

import com.example.concessionaria_campos.dto.SaleDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidatorCurtomer implements ConstraintValidator<ValidCustomer, SaleDTO> {

    @Override
    public boolean isValid(SaleDTO dto, ConstraintValidatorContext constraintValidatorContext) {
        return dto.getCustomer() != null || dto.getCustomer().getId() != null;
    }
}
