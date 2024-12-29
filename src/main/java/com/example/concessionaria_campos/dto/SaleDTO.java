package com.example.concessionaria_campos.dto;

import com.example.concessionaria_campos.entity.Customer;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SaleDTO {

    private Long id;

    private LocalDateTime date;

    @NotNull(message = "O campo 'cliente' é obrigatório", groups = {Create.class, Update.class})
    private CustomerDTO customer;

    @NotNull(message = "O campo 'veículo' é obrigatório", groups = {Create.class, Update.class})
    private VehicleDTO vehicle;
}
