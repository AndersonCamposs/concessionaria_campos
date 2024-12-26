package com.example.concessionaria_campos.assembler;

import com.example.concessionaria_campos.controller.CustomerController;
import com.example.concessionaria_campos.dto.CustomerDTO;
import com.example.concessionaria_campos.entity.Customer;
import com.example.concessionaria_campos.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class CustomerDTOAssembler implements RepresentationModelAssembler<Customer, CustomerDTO> {

    @Autowired
    CustomerMapper customerMapper;

    @Override
    public CustomerDTO toModel(Customer entity) {
        CustomerDTO customerDTO = customerMapper.toDTO(entity);

        //adiciona os hiperlinks
        customerDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class)
                .fetchById(entity.getId())).withSelfRel());
        customerDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class)
                .fetchAll()).withRel("all-customers"));

        return customerDTO;
    }
}
