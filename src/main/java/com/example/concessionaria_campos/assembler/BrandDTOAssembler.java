package com.example.concessionaria_campos.assembler;

import com.example.concessionaria_campos.controller.BrandController;
import com.example.concessionaria_campos.dto.BrandDTO;
import com.example.concessionaria_campos.entity.Brand;
import com.example.concessionaria_campos.mapper.BrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class BrandDTOAssembler implements RepresentationModelAssembler<Brand, BrandDTO> {

    @Autowired
    BrandMapper brandMapper;

    @Override
    public BrandDTO toModel(Brand entity) {
        BrandDTO brandDTO = brandMapper.toDTO(entity);

        brandDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BrandController.class)
                .fetchById(entity.getId())).withSelfRel());
        brandDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BrandController.class)
                .fetchAll()).withRel("all-brands"));

        return brandDTO;
    }
}
