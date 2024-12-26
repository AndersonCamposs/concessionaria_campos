package com.example.concessionaria_campos.controller;

import com.example.concessionaria_campos.assembler.CustomerDTOAssembler;
import com.example.concessionaria_campos.dto.ApiResponse;
import com.example.concessionaria_campos.dto.CustomerDTO;
import com.example.concessionaria_campos.dto.Create;
import com.example.concessionaria_campos.dto.Update;
import com.example.concessionaria_campos.mapper.CustomerMapper;
import com.example.concessionaria_campos.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/cliente")
@Tag(name = "Clientes", description = "Gerenciar os clientes daa concessionária.")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    CustomerDTOAssembler customerDTOAssembler;
    @Autowired
    CustomerMapper customerMapper;

    @PostMapping
    @Operation(
            summary = "Cadastra um novo cliente na base de dados",
            description = "Retorna os dados do cliente recém inserido em formato JSON.",
            responses = {
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "A requisição foi bem sucedida e um registro foi criado no banco."),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Houve algum erro de validação nos dados enviados no corpo da requisição.")
            }
    )
    public ResponseEntity<CustomerDTO> saveCustomer(@RequestBody @Validated(Create.class) CustomerDTO data) {
        CustomerDTO savedCustomer = customerService.saveCustomer(data);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(customerDTOAssembler.toModel(customerMapper.toEntity(savedCustomer)));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualiza os dados de um cliente, como regra de negócio, apenas o nome do cliente pode ser alterado.",
            description = "Retorna os dados do usuário atualizado em formato JSON.",
            responses = {
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "A requisição foi bem sucedida e o registro foi atualizado no banco"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Houve algum erro de validação nos dados enviados no corpo da requisição.")
            }
    )
    public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody @Validated(Update.class) CustomerDTO data, @PathVariable Long id) {
        CustomerDTO updatedCustomer = customerService.updateCustomer(data, id);
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDTOAssembler.toModel(customerMapper.toEntity(updatedCustomer)));
    }

    @GetMapping
    @Operation(
            summary = "Lista completa de todos os clientes.",
            description = "Retorna uma lista com os dados de todos os clientes em formato JSON.",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "A requisição foi bem sucedida e foi retornada a lista com todos os clientes."),
            }
    )
    public ResponseEntity<List<CustomerDTO>> fetchAll() {
        List<CustomerDTO> customerList = customerService.fetchAll()
                .stream()
                .map(customerDTO -> customerDTOAssembler.toModel(customerMapper.toEntity(customerDTO)))
                .collect(Collectors.toList());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerList);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Lista os dados de um cliente.",
            description = "Retorna os dados de um cliente específico no formato JSON, com base no ID recebido com Path Variable.",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "A requisição foi bem sucedida e foi retornado o registro com os dados do cliente."),
            }
    )
    public ResponseEntity<CustomerDTO> fetchById(@PathVariable Long id) {
        CustomerDTO existingCustomer = customerService.fetchById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDTOAssembler.toModel(customerMapper.toEntity(existingCustomer)));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Deleta os dados de um cliente",
            description = "Retorna um objeto ApiResponse contendo uma mensagem de confirmação caso a requisição seja bem sucedida",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "A requisição foi bem sucedida, o registro foi deletado e foi retornado um JSON de confirmação."),
            }
    )
    public ResponseEntity<ApiResponse> deleteCustomer(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerService.deleteCustomer(id));
    }
}
