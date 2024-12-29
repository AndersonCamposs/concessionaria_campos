package com.example.concessionaria_campos.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidatorCurtomer.class)
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCustomer {
    String message() default "O campo 'cliente' é obrigatório";
    Class<?>[] groups() default  {};
    Class<? extends Payload>[] payload() default {};
}
