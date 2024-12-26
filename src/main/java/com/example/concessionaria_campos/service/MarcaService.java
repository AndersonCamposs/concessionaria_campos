package com.example.concessionaria_campos.service;

import com.example.concessionaria_campos.dto.ApiResponse;
import com.example.concessionaria_campos.dto.MarcaDTO;
import com.example.concessionaria_campos.entity.Marca;
import com.example.concessionaria_campos.exception.ResourceNotFoundException;
import com.example.concessionaria_campos.mapper.MarcaMapper;
import com.example.concessionaria_campos.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public MarcaDTO atualizarMarca(MarcaDTO marca, MultipartFile file, Long id) {
        Marca marcaExistente = marcaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Marca não encontrada"));
        try {
            if (file != null && !file.isEmpty()) {
                marcaExistente.setFoto(fileStorageService.saveFile(file, "marcas"));
            }
            marcaExistente.setNome(marca.getNome());
            Marca marcaAtualizada = marcaRepository.save(marcaExistente);

            return marcaMapper.toDTO(marcaAtualizada);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar arquivo.");
        }
    }

    public List<MarcaDTO> listarMarcas() {
        List<MarcaDTO> listaMarcas = new ArrayList<>();
        marcaRepository.findAll()
                .forEach(marca -> listaMarcas.add(marcaMapper.toDTO(marca)));
        return listaMarcas;
    }

    public MarcaDTO buscarPorId(Long id) {
        Marca marcaExistente = marcaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Marca não encontrada"));
        return marcaMapper.toDTO(marcaExistente);
    }

    public ApiResponse deletarMarca(Long id) {
        Marca marcaExistente = marcaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Marca não encontrada"));
        marcaRepository.deleteById(id);
        return new ApiResponse("Marca deletada com sucesso");
    }
}
