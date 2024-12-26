package com.example.concessionaria_campos.assembler;

import com.example.concessionaria_campos.controller.MarcaController;
import com.example.concessionaria_campos.dto.MarcaDTO;
import com.example.concessionaria_campos.entity.Marca;
import com.example.concessionaria_campos.mapper.MarcaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

public class MarcaDTOAssembler implements RepresentationModelAssembler<Marca, MarcaDTO> {

    @Autowired
    MarcaMapper marcaMapper;

    @Override
    public MarcaDTO toModel(Marca entity) {
        MarcaDTO marcaDTO = marcaMapper.toDTO(entity);

        return new MarcaDTO();
    }
}
