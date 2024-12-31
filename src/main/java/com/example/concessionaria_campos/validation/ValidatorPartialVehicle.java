package com.example.concessionaria_campos.validation;

import com.example.concessionaria_campos.dto.VehicleDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidatorPartialVehicle implements ConstraintValidator<ValidPartialVehicle, VehicleDTO> {

    @Override
    public boolean isValid(VehicleDTO vehicleDTO, ConstraintValidatorContext constraintValidatorContext) {
        return vehicleDTO != null && vehicleDTO.getId() != null;
    }
}
