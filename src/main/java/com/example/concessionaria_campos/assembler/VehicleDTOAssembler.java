package com.example.concessionaria_campos.assembler;

import com.example.concessionaria_campos.controller.VehicleController;
import com.example.concessionaria_campos.dto.VehicleDTO;
import com.example.concessionaria_campos.entity.Vehicle;
import com.example.concessionaria_campos.mapper.VehicleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class VehicleDTOAssembler implements RepresentationModelAssembler<Vehicle, VehicleDTO> {

    @Autowired
    private VehicleMapper vehicleMapper;
    @Autowired
    private BrandDTOAssembler brandDTOAssembler;
    @Autowired
    private CategoryDTOAssembler categoryDTOAssembler;

    @Override
    public VehicleDTO toModel(Vehicle entity) {
        VehicleDTO vehicleDTO = vehicleMapper.toDTO(entity);

        vehicleDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(VehicleController.class)
                .fetchById(entity.getId())).withSelfRel());
        vehicleDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(VehicleController.class)
                .fetchAll()).withRel("all-vehicles"));

        vehicleDTO.setBrand(brandDTOAssembler.toModel(entity.getBrand()));
        vehicleDTO.setCategory(categoryDTOAssembler.toModel(entity.getCategory()));

        return vehicleDTO;
    }
}
