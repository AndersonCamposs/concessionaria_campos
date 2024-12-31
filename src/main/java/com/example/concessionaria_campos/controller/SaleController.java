package com.example.concessionaria_campos.controller;

import com.example.concessionaria_campos.assembler.SaleDTOAssembler;
import com.example.concessionaria_campos.dto.ApiResponse;
import com.example.concessionaria_campos.dto.Create;
import com.example.concessionaria_campos.dto.SaleDTO;
import com.example.concessionaria_campos.mapper.SaleMapper;
import com.example.concessionaria_campos.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sale")
public class SaleController {
    @Autowired
    private SaleService saleService;
    @Autowired
    private SaleMapper saleMapper;
    @Autowired
    private SaleDTOAssembler saleDTOAssembler;

    @PostMapping
    public ResponseEntity<SaleDTO> saveSale(@RequestBody @Validated(Create.class) SaleDTO data) {
        SaleDTO savedSale = saleService.saveSale(data);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedSale);
    }

    //@PutMapping("/{id}")

    @GetMapping
    public ResponseEntity<List<SaleDTO>> fetchAll() {
        List<SaleDTO> salesList = saleService.fetchAll()
                .stream()
                .map(saleDTO -> saleDTOAssembler.toModel(saleMapper.toEntity(saleDTO)))
                .toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(salesList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleDTO> fetchById(@PathVariable Long id) {
        SaleDTO existingSale = saleService.fetchById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(saleDTOAssembler.toModel(saleMapper.toEntity(existingSale)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteSale(@PathVariable Long id) {
        ApiResponse response = saleService.deleteSale(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
