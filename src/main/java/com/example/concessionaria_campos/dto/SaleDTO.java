package com.example.concessionaria_campos.dto;

import com.example.concessionaria_campos.validation.ValidPartialCustomer;
import com.example.concessionaria_campos.validation.ValidPartialVehicle;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Getter
@Setter
public class SaleDTO extends RepresentationModel<SaleDTO> {

    private Long id;

    private LocalDateTime date;

    @ValidPartialCustomer(message = "O campo 'cliente' é obrigatório", groups = {Create.class, Update.class})
    private CustomerDTO customer;

    @ValidPartialVehicle(message = "O campo 'veículo' é obrigatório", groups = {Create.class, Update.class})
    private VehicleDTO vehicle;
}
