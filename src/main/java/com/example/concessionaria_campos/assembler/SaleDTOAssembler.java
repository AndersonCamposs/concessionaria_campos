package com.example.concessionaria_campos.assembler;

import com.example.concessionaria_campos.controller.SaleController;
import com.example.concessionaria_campos.dto.SaleDTO;
import com.example.concessionaria_campos.entity.Sale;
import com.example.concessionaria_campos.mapper.SaleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class SaleDTOAssembler implements RepresentationModelAssembler<Sale, SaleDTO> {
    @Autowired
    private SaleMapper saleMapper;
    @Autowired
    private CustomerDTOAssembler customerDTOAssembler;
    @Autowired
    private VehicleDTOAssembler vehicleDTOAssembler;

    @Override
    public SaleDTO toModel(Sale entity) {
        SaleDTO saleDTO = saleMapper.toDTO(entity);

        saleDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SaleController.class)
                .fetchById(entity.getId())).withSelfRel());
        saleDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SaleController.class)
                .fetchAll()).withRel("all-sales"));

        saleDTO.setCustomer(customerDTOAssembler.toModel(entity.getCustomer()));
        saleDTO.setVehicle(vehicleDTOAssembler.toModel(entity.getVehicle()));

        return saleDTO;
    }
}
