package com.example.concessionaria_campos.controller;

import com.example.concessionaria_campos.dto.ApiResponse;
import com.example.concessionaria_campos.dto.ClienteDTO;
import com.example.concessionaria_campos.dto.Create;
import com.example.concessionaria_campos.dto.Update;
import com.example.concessionaria_campos.entity.Cliente;
import com.example.concessionaria_campos.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value = "/cliente")
@Tag(name = "Clientes", description = "Gerenciar os clientes daa concessionária.")
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @PostMapping
    @Operation(
            summary = "Cadastra um novo cliente na base de dados",
            description = "Retorna os dados do cliente recém inserido em formato JSON.",
            responses = {
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "A requisição foi bem sucedida e um registro foi criado no banco."),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Houve algum erro de validação nos dados enviados no corpo da requisição.")
            }
    )
    public ResponseEntity<ClienteDTO> salvarCliente(@RequestBody @Validated(Create.class) ClienteDTO data) {
        ClienteDTO clienteSalvo = clienteService.salvarCliente(data);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clienteSalvo);
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
    public ResponseEntity<ClienteDTO> atualizarCliente(@RequestBody @Validated(Update.class) ClienteDTO data, @PathVariable Long id) {
        ClienteDTO clienteAtualizado = clienteService.atualizarCliente(data, id);
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(clienteAtualizado);
    }

    @GetMapping
    @Operation(
            summary = "Lista completa de todos os clientes.",
            description = "Retorna uma lista com os dados de todos os clientes em formato JSON.",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "A requisição foi bem sucedida e foi retornada a lista com todos os clientes."),
            }
    )
    public ResponseEntity<List<ClienteDTO>> listarClientes() {
        List<ClienteDTO> listaClientes = clienteService.listarClientes();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(listaClientes);
    }

    @GetMapping("{id}")
    @Operation(
            summary = "Lista os dados de um cliente.",
            description = "Retorna os dados de um cliente específico no formato JSON, com base no ID recebido com Path Variable.",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "A requisição foi bem sucedida e foi retornado o registro com os dados do cliente."),
            }
    )
    public ResponseEntity<ClienteDTO> listarCliente(@PathVariable Long id) {
        ClienteDTO clienteExistente = clienteService.listarCliente(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clienteExistente);
    }

    @DeleteMapping("{id}")
    @Operation(
            summary = "Deleta os dados de um cliente",
            description = "Retorna um objeto ApiResponse contendo uma mensagem de confirmação caso a requisição seja bem sucedida",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "A requisição foi bem sucedida, o registro foi deletado e foi retornado um JSON de confirmação."),
            }
    )
    public ResponseEntity<ApiResponse> deletarCliente(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clienteService.deletarCliente(id));
    }
}
