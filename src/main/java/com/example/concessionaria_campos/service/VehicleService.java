package com.example.concessionaria_campos.service;

import com.example.concessionaria_campos.dto.VehicleDTO;
import com.example.concessionaria_campos.mapper.BrandMapper;
import com.example.concessionaria_campos.mapper.CategoryMapper;
import com.example.concessionaria_campos.mapper.VehicleMapper;
import com.example.concessionaria_campos.param.VehiclePO;
import com.example.concessionaria_campos.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {
    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    VehicleMapper vehicleMapper;
    @Autowired
    BrandService brandService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    BrandMapper brandMapper;
    @Autowired
    CategoryMapper categoryMapper;

    public VehicleDTO saveVehicle(VehicleDTO vehicle) {

        return vehicle;
    }

    public VehicleDTO convertPOToDto(VehiclePO vehiclePO) {
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setModel(vehiclePO.getModel());
        vehicleDTO.setChassisNumber(vehiclePO.getChassisNumber());
        vehicleDTO.setPlate(vehiclePO.getPlate());
        vehicleDTO.setBrand(brandMapper.toEntity(brandService.fetchById(vehiclePO.getBrandId())));
        vehicleDTO.setYear(vehiclePO.getYear());
        vehicleDTO.setCategory(categoryMapper.toEntity(categoryService.fetchById(vehiclePO.getCategoryId())));
        vehicleDTO.setTransmissionType(vehiclePO.getTransmissionType());
        vehicleDTO.setStatus(vehiclePO.getStatus());

        return vehicleDTO;
    }
}
