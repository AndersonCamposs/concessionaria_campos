package com.example.concessionaria_campos.controller;

import com.example.concessionaria_campos.dto.MarcaDTO;
import com.example.concessionaria_campos.service.MarcaService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/marca")
public class MarcaController {

    @Autowired
    MarcaService marcaService;

    @PostMapping
    public ResponseEntity<?> salvarMarca(
            @RequestParam("nome") String nome,
            @RequestParam(value = "foto", required = false) MultipartFile foto
    ) {
        MarcaDTO data = new MarcaDTO();
        data.setNome(nome);
        MarcaDTO marcaSalva = marcaService.salvarMarca(data, foto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(marcaSalva);
    }
}
