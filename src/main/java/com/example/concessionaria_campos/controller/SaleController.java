package com.example.concessionaria_campos.controller;

import com.example.concessionaria_campos.dto.Create;
import com.example.concessionaria_campos.dto.SaleDTO;
import com.example.concessionaria_campos.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sale")
public class SaleController {
    @Autowired
    SaleService saleService;

    @PostMapping
    public ResponseEntity<SaleDTO> saveSale(@RequestBody @Validated(Create.class) SaleDTO data) {
        SaleDTO savedSale = saleService.saveSale(data);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedSale);
    }
}
