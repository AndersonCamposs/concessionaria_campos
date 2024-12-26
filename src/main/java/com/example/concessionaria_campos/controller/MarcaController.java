package com.example.concessionaria_campos.controller;

import com.example.concessionaria_campos.dto.Create;
import com.example.concessionaria_campos.dto.MarcaDTO;
import com.example.concessionaria_campos.service.MarcaService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/marca")
public class MarcaController {

    @Autowired
    MarcaService marcaService;

    @PostMapping
    public ResponseEntity<?> salvarMarca(
            @Validated(Create.class) @ModelAttribute MarcaDTO data,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) {
        MarcaDTO marcaSalva = marcaService.salvarMarca(data, file);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(marcaSalva);
    }
}
