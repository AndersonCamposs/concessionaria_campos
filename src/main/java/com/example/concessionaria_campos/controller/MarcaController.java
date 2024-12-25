package com.example.concessionaria_campos.controller;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/marca")
public class MarcaController {

    @PostMapping
    public ResponseEntity<?> salvarMarca(
            @RequestParam("nome") String nome,
            @RequestParam("foto") MultipartFile foto
    ) {
        System.out.println("ok");
        return ResponseEntity.ok().build();
    }
}
