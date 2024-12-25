package com.example.concessionaria_campos.service;

import com.example.concessionaria_campos.dto.ApiResponse;
import com.example.concessionaria_campos.dto.ClienteDTO;
import com.example.concessionaria_campos.entity.Cliente;
import com.example.concessionaria_campos.exception.ResourceNotFoundException;
import com.example.concessionaria_campos.mapper.ClienteMapper;
import com.example.concessionaria_campos.repository.ClienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public ClienteDTO atualizarCliente(ClienteDTO cliente, Long id) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
        clienteExistente.setNome(cliente.getNome());
        Cliente clienteAtualizado = clienteRepository.save(clienteExistente);
        return clienteMapper.toDTO(clienteAtualizado);
    }

    public List<ClienteDTO> listarClientes() {
        List<ClienteDTO> listaClientes = new ArrayList<>();
        clienteRepository.findAll()
                .forEach(cliente -> listaClientes.add(clienteMapper.toDTO(cliente)));
        return listaClientes;
    }

    public ClienteDTO listarCliente(Long id) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente de não encontrado"));
        return clienteMapper.toDTO(clienteExistente);
    }

    public ApiResponse deletarCliente(Long id) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente de não encontrado"));
        clienteRepository.deleteById(clienteExistente.getId());
        return new ApiResponse("Cliente deletado com sucesso");
    }
}
