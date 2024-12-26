package com.example.concessionaria_campos.controller;

import com.example.concessionaria_campos.assembler.MarcaDTOAssembler;
import com.example.concessionaria_campos.dto.Create;
import com.example.concessionaria_campos.dto.MarcaDTO;
import com.example.concessionaria_campos.dto.Update;
import com.example.concessionaria_campos.mapper.MarcaMapper;
import com.example.concessionaria_campos.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/marca")
public class MarcaController {

    @Autowired
    MarcaService marcaService;
    @Autowired
    MarcaDTOAssembler marcaDTOAssembler;
    @Autowired
    MarcaMapper marcaMapper;

    @PostMapping
    public ResponseEntity<MarcaDTO> salvarMarca(
            @Validated(Create.class) @ModelAttribute MarcaDTO data,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) {
        MarcaDTO marcaSalva = marcaService.salvarMarca(data, file);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(marcaSalva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarcaDTO> atualizarMarca(
            @Validated(Update.class) @ModelAttribute MarcaDTO data,
            @RequestParam(value = "file", required = false) MultipartFile file,
            @PathVariable Long id
    ) {
        MarcaDTO marcaSalva = marcaService.atualizarMarca(data, file, id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(marcaSalva);
    }

    @GetMapping
    public ResponseEntity<List<MarcaDTO>> listarMarcas() {
        List<MarcaDTO> listaMarcas = marcaService.listarMarcas()
                .stream()
                .map(marcaDTO -> marcaDTOAssembler.toModel(marcaMapper.toEntity(marcaDTO)))
                .collect(Collectors.toList());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(listaMarcas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarcaDTO> buscarPorId(@PathVariable Long id) {
        MarcaDTO marcaExistente = marcaService.buscarPorId(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(marcaDTOAssembler.toModel(marcaMapper.toEntity(marcaExistente)));
    }
}
