package com.example.concessionaria_campos.controller;

import com.example.concessionaria_campos.assembler.BrandDTOAssembler;
import com.example.concessionaria_campos.dto.ApiResponse;
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
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;
    @Autowired
    private BrandDTOAssembler brandDTOAssembler;
    @Autowired
    private BrandMapper brandMapper;

    @PostMapping
    public ResponseEntity<BrandDTO> saveBrand(
            @Validated(Create.class) @ModelAttribute BrandDTO data,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) {
        BrandDTO savedBrand = brandService.saveBrand(data, file);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(brandDTOAssembler.toModel(brandMapper.toEntity(savedBrand)));
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
                .body((brandDTOAssembler.toModel(brandMapper.toEntity(updatedBrand))));
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

    @GetMapping("/search")
    public ResponseEntity<List<BrandDTO>> fetchByName(@RequestParam(value = "name") String name) {
        List<BrandDTO> brandList = brandService.fetchByName(name)
                .stream()
                .map(brandDTO -> brandDTOAssembler.toModel(brandMapper.toEntity(brandDTO)))
                .toList();

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

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteBrand(@PathVariable Long id) {
        ApiResponse response = brandService.deleteBrand(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

}
