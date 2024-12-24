package com.example.concessionaria_campos.controller;

import com.example.concessionaria_campos.dto.ClienteDTO;
import com.example.concessionaria_campos.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cliente")
@Tag(name = "Clientes", description = "Gerenciar os clientes daa concessionária.")
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @PostMapping
    @Operation(summary = "Cadastra um novo cliente na base de dados", description = "Retorna os dados do cliente recém inserido em fromato JSON.")
    public ResponseEntity<ClienteDTO> salvarCliente(@RequestBody ClienteDTO data) {
        ClienteDTO clienteSalvo = clienteService.salvarCliente(data);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clienteSalvo);
    }
}
