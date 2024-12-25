package com.example.concessionaria_campos.validation;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



public class CPFValidator implements ConstraintValidator<ValidCPF, String> {

    @Override
    public boolean isValid(String cpfOriginal, ConstraintValidatorContext constraintValidatorContext) {
        cpfOriginal = cpfOriginal.replaceAll("[^0-9]", "");
        String cpfValido = cpfOriginal.substring(0, 8);

        int somaUm = 0;
        for (int i = 0; i < 9; i++) {
            somaUm += (cpfValido.charAt(i) - '0') * (10 - i);
        }

        int restoUm = somaUm % 11;
        int digitoUm = (restoUm < 2) ? 0 : 11 - restoUm;
        cpfValido += Integer.toString(digitoUm);

        int somaDois = 0;
        for (int i = 0; i < 10; i++) {
            somaDois += (cpfValido.charAt(i) - '0') * (11 - i);
        }

        int restoDois = somaDois % 11;
        int digitoDois = restoDois < 2 ? 0 : 11 - restoDois;
        cpfValido += Integer.toString(digitoDois);


        return cpfValido.equals(cpfOriginal);
    }
}
