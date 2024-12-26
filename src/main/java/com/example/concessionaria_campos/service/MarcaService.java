package com.example.concessionaria_campos.service;

import com.example.concessionaria_campos.dto.MarcaDTO;
import com.example.concessionaria_campos.entity.Marca;
import com.example.concessionaria_campos.mapper.MarcaMapper;
import com.example.concessionaria_campos.repository.MarcaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class MarcaService {
    @Autowired
    MarcaRepository marcaRepository;
    @Autowired
    MarcaMapper marcaMapper;
    @Autowired
    FileStorageService fileStorageService;

    public MarcaDTO salvarMarca(@Validated MarcaDTO marca, MultipartFile file) {
        try {
            if (file != null && !file.isEmpty()) {
                marca.setFoto(fileStorageService.saveFile(file, "marcas"));
            }
            Marca marcaSalva = marcaRepository.save(marcaMapper.toEntity(marca));

            return marcaMapper.toDTO(marcaSalva);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar arquivo.");
        }

    }

    public MarcaDTO atualizarMarca(MarcaDTO marca, MultipartFile file) {
        return new MarcaDTO();
    }
}
