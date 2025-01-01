package com.example.concessionaria_campos.service;

import com.example.concessionaria_campos.dto.ApiResponse;
import com.example.concessionaria_campos.dto.CustomerDTO;
import com.example.concessionaria_campos.entity.Customer;
import com.example.concessionaria_campos.exception.ResourceNotFoundException;
import com.example.concessionaria_campos.mapper.CustomerMapper;
import com.example.concessionaria_campos.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerMapper customerMapper;

    public CustomerDTO saveCustomer(CustomerDTO customer) {
        try {
            Customer savedCustomer = customerRepository.save(customerMapper.toEntity(customer));
            return customerMapper.toDTO(savedCustomer);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar os dados do cliente");
        }
    }

    public CustomerDTO updateCustomer(CustomerDTO customer, Long id) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
        existingCustomer.setName(customer.getName());
        existingCustomer.setEmail(customer.getEmail());
        Customer updatedCustomer = customerRepository.save(existingCustomer);
        return customerMapper.toDTO(updatedCustomer);
    }

    public List<CustomerDTO> fetchAll() {
        return customerRepository
                .findAll()
                .stream()
                .map(customerMapper::toDTO)
                .toList();
    }

    public CustomerDTO fetchById(Long id) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente de não encontrado"));
        return customerMapper.toDTO(existingCustomer);
    }

    public ApiResponse deleteCustomer(Long id) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente de não encontrado"));
        customerRepository.deleteById(existingCustomer.getId());
        return new ApiResponse("Cliente deletado com sucesso");
    }
}
