package com.example.concessionaria_campos.service;

import com.example.concessionaria_campos.dto.ApiResponse;
import com.example.concessionaria_campos.dto.BrandDTO;
import com.example.concessionaria_campos.entity.Brand;
import com.example.concessionaria_campos.exception.ResourceNotFoundException;
import com.example.concessionaria_campos.mapper.BrandMapper;
import com.example.concessionaria_campos.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BrandService {
    @Autowired
    BrandRepository brandRepository;
    @Autowired
    BrandMapper brandMapper;
    @Autowired
    FileStorageService fileStorageService;

    public BrandDTO saveBrand(@Validated BrandDTO brand, MultipartFile file) {
        try {
            if (file != null && !file.isEmpty()) {
                brand.setImage(fileStorageService.saveFile(file, "brands"));
            }
            Brand savedBrand = brandRepository.save(brandMapper.toEntity(brand));

            return brandMapper.toDTO(savedBrand);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar arquivo.");
        }

    }

    public BrandDTO updateBrand(BrandDTO brand, MultipartFile file, Long id) {
        Brand existingBrand = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Marca não encontrada"));
        try {
            if (file != null && !file.isEmpty()) {
                existingBrand.setImage(fileStorageService.saveFile(file, "brands"));
            }
            existingBrand.setName(brand.getName());
            Brand updatedBrand = brandRepository.save(existingBrand);

            return brandMapper.toDTO(updatedBrand);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar arquivo.");
        }
    }

    public List<BrandDTO> fetchAll() {
        List<BrandDTO> brandList = new ArrayList<>();
        brandRepository.findAll()
                .forEach(brand -> brandList.add(brandMapper.toDTO(brand)));
        return brandList;
    }

    public BrandDTO fetchById(Long id) {
        Brand existingBrand = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Marca não encontrada"));
        return brandMapper.toDTO(existingBrand);
    }

    public ApiResponse deleteBrand(Long id) {
        Brand existingBrand = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Marca não encontrada"));
        brandRepository.deleteById(id);
        return new ApiResponse("Marca deletada com sucesso");
    }
}