package com.example.concessionaria_campos.controller;

import com.example.concessionaria_campos.assembler.BrandDTOAssembler;
import com.example.concessionaria_campos.dto.BrandDTO;
import com.example.concessionaria_campos.dto.Create;
import com.example.concessionaria_campos.dto.Update;
import com.example.concessionaria_campos.mapper.BrandMapper;
import com.example.concessionaria_campos.service.BrandService;
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
public class BrandController {

    @Autowired
    BrandService brandService;
    @Autowired
    BrandDTOAssembler brandDTOAssembler;
    @Autowired
    BrandMapper brandMapper;

    @PostMapping
    public ResponseEntity<BrandDTO> saveBrand(
            @Validated(Create.class) @ModelAttribute BrandDTO data,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) {
        BrandDTO savedBrand = brandService.saveBrand(data, file);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedBrand);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrandDTO> updateBrand(
            @Validated(Update.class) @ModelAttribute BrandDTO data,
            @RequestParam(value = "file", required = false) MultipartFile file,
            @PathVariable Long id
    ) {
        BrandDTO updatedBrand = brandService.updateBrand(data, file, id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedBrand);
    }

    @GetMapping
    public ResponseEntity<List<BrandDTO>> fetchAll() {
        List<BrandDTO> brandList = brandService.fetchAll()
                .stream()
                .map(brandDTO -> brandDTOAssembler.toModel(brandMapper.toEntity(brandDTO)))
                .collect(Collectors.toList());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(brandList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandDTO> fetchById(@PathVariable Long id) {
        BrandDTO existingBrand = brandService.fetchById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(brandDTOAssembler.toModel(brandMapper.toEntity(existingBrand)));
    }
}
