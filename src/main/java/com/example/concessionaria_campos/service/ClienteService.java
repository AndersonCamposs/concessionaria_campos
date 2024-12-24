package com.example.concessionaria_campos.service;

import com.example.concessionaria_campos.dto.ClienteDTO;
import com.example.concessionaria_campos.entity.Cliente;
import com.example.concessionaria_campos.mapper.ClienteMapper;
import com.example.concessionaria_campos.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ClienteMapper clienteMapper;

    public ClienteDTO salvarCliente(ClienteDTO cliente) {
        try {
            Cliente clienteSalvo = clienteRepository.save(clienteMapper.toEntity(cliente));
            return clienteMapper.toDTO(clienteSalvo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
