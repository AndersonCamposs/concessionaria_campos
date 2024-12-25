package com.example.concessionaria_campos.assembler;

import com.example.concessionaria_campos.controller.ClienteController;
import com.example.concessionaria_campos.dto.ClienteDTO;
import com.example.concessionaria_campos.entity.Cliente;
import com.example.concessionaria_campos.mapper.ClienteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class ClienteDTOAssembler implements RepresentationModelAssembler<Cliente, ClienteDTO> {

    @Autowired
    ClienteMapper clienteMapper;

    @Override
    public ClienteDTO toModel(Cliente entity) {
        ClienteDTO clienteDTO = clienteMapper.toDTO(entity);

        //adiciona os hiperlinks
        clienteDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ClienteController.class)
                .listarCliente(entity.getId())).withSelfRel());
        clienteDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ClienteController.class)
                .listarClientes()).withRel("todos-os-clientes"));

        return clienteDTO;
    }
}
