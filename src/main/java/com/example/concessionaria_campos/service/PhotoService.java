package com.example.concessionaria_campos.service;

import com.example.concessionaria_campos.dto.PhotoDTO;
import com.example.concessionaria_campos.entity.Photo;
import com.example.concessionaria_campos.entity.Vehicle;
import com.example.concessionaria_campos.mapper.PhotoMapper;
import com.example.concessionaria_campos.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhotoService {
    @Autowired
    private PhotoRepository photoRepository;
    @Autowired
    private PhotoMapper photoMapper;

    public List<PhotoDTO> saveManyPhotos(List<PhotoDTO> dtosList) {
        List<Photo> photosList = dtosList
                .stream()
                .map(photoMapper::toEntity)
                .toList();

        List<Photo> savedPhotos = photoRepository.saveAll(photosList);

        return savedPhotos
                .stream()
                .map(photoMapper::toDTO)
                .toList();
    }

    public List<PhotoDTO> fetchByVehicle(Vehicle vehicle) {
        return photoRepository
                .findByVehicle(vehicle)
                .stream()
                .map(photoMapper::toDTO)
                .toList();
    }
}
