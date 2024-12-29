package com.example.concessionaria_campos.service;

import com.example.concessionaria_campos.dto.PhotoDTO;
import com.example.concessionaria_campos.dto.VehicleDTO;
import com.example.concessionaria_campos.entity.Vehicle;
import com.example.concessionaria_campos.exception.ResourceNotFoundException;
import com.example.concessionaria_campos.mapper.BrandMapper;
import com.example.concessionaria_campos.mapper.CategoryMapper;
import com.example.concessionaria_campos.mapper.VehicleMapper;
import com.example.concessionaria_campos.param.VehiclePO;
import com.example.concessionaria_campos.repository.VehicleRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private VehicleMapper vehicleMapper;
    @Autowired
    private BrandService brandService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private PhotoService photoService;
    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    public VehicleDTO saveVehicle(VehicleDTO vehicle, List<MultipartFile> files) {
        try {
            fileStorageService.validate(files);;
            Vehicle savedVehicle = vehicleRepository.save(vehicleMapper.toEntity(vehicle));
            if (!files.isEmpty()) {
                List<PhotoDTO> savedFiles = fileStorageService.saveFiles(files, "vehicles");
                for (PhotoDTO photo: savedFiles) {
                    photo.setVehicle(savedVehicle);
                }
                photoService.saveManyPhotos(savedFiles);
            }

            return vehicleMapper.toDTO(savedVehicle);

        } catch (IOException e) {
            throw  new RuntimeException("Erro ao salvar arquivo(s)");
        }
    }

    public VehicleDTO updateVehicle(VehicleDTO vehicle, List<MultipartFile> files, Long id) {
        fileStorageService.validate(files);

        Vehicle existingVehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Veículo não encontrado"));
        try {
            List<PhotoDTO> vehiclePhotos = photoService.fetchByVehicle(existingVehicle);
            fileStorageService.deleteFiles(vehiclePhotos);

            existingVehicle.getPhotos().clear();
            vehicle.setPhotos(existingVehicle.getPhotos());
            BeanUtils.copyProperties(vehicle, existingVehicle);
            existingVehicle.setId(id);

            Vehicle updatedVehicle = vehicleRepository.save(existingVehicle);

            if (!files.isEmpty()) {
                List<PhotoDTO> savedFiles = fileStorageService.saveFiles(files, "vehicles");
                for (PhotoDTO photo: savedFiles) {
                    photo.setVehicle(updatedVehicle);
                    photoService.saveManyPhotos(savedFiles);
                }
            }

            return vehicleMapper.toDTO(updatedVehicle);

        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar arquivos");
        }
    }

    public List<VehicleDTO> fetchAll() {
        return vehicleRepository
                .findAll()
                .stream()
                .map(vehicleMapper::toDTO)
                .toList();
    }

    public VehicleDTO fetchById(Long id) {
        Vehicle existingVehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Veículo não encontrado"));
        return vehicleMapper.toDTO(existingVehicle);
    }

    public VehicleDTO convertPOToDto(VehiclePO vehiclePO) {
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setModel(vehiclePO.getModel());
        vehicleDTO.setChassisNumber(vehiclePO.getChassisNumber());
        vehicleDTO.setPlate(vehiclePO.getPlate());
        vehicleDTO.setBrand(brandService.fetchById(vehiclePO.getBrandId()));
        vehicleDTO.setYear(vehiclePO.getYear());
        vehicleDTO.setCategory(categoryService.fetchById(vehiclePO.getCategoryId()));
        vehicleDTO.setTransmissionType(vehiclePO.getTransmissionType());
        vehicleDTO.setStatus(vehiclePO.getStatus());

        return vehicleDTO;
    }
}
