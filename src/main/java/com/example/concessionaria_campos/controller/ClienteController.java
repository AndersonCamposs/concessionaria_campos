package com.example.concessionaria_campos.controller;

import com.example.concessionaria_campos.dto.ClienteDTO;
import com.example.concessionaria_campos.entity.Cliente;
import com.example.concessionaria_campos.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping(value = "/cliente")
@Tag(name = "Clientes", description = "Gerenciar os clientes daa concessionária.")
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @PostMapping
    @Operation(summary = "Cadastra um novo cliente na base de dados", description = "Retorna os dados do cliente recém inserido em formato JSON.")
    public ResponseEntity<ClienteDTO> salvarCliente(@RequestBody ClienteDTO data) {
        ClienteDTO clienteSalvo = clienteService.salvarCliente(data);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clienteSalvo);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza os dados de um cliente, como regra de negócio, apenas o nome do cliente pode ser alterado.", description = "Retorna os dados do usuário atualizado em formato JSON.")
    public ResponseEntity<ClienteDTO> atualizarCliente(@RequestBody ClienteDTO data, @PathVariable Long id) {
        ClienteDTO clienteAtualizado = clienteService.atualizarCliente(data, id);
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(clienteAtualizado);
    }
}
