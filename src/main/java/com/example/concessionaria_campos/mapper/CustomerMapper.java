package com.example.concessionaria_campos.mapper;

import com.example.concessionaria_campos.dto.CustomerDTO;
import com.example.concessionaria_campos.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toEntity(CustomerDTO dto);

    CustomerDTO toDTO(Customer entity);

}
