package com.example.concessionaria_campos.controller;

import com.example.concessionaria_campos.assembler.VehicleDTOAssembler;
import com.example.concessionaria_campos.dto.ApiResponse;
import com.example.concessionaria_campos.dto.Create;
import com.example.concessionaria_campos.dto.Update;
import com.example.concessionaria_campos.dto.VehicleDTO;
import com.example.concessionaria_campos.enums.VehicleStatus;
import com.example.concessionaria_campos.mapper.VehicleMapper;
import com.example.concessionaria_campos.param.VehiclePO;
import com.example.concessionaria_campos.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private VehicleDTOAssembler vehicleDTOAssembler;
    @Autowired
    private VehicleMapper vehicleMapper;

    @PostMapping
    public ResponseEntity<VehicleDTO> saveVehicle(
            @Validated(Create.class) @ModelAttribute VehiclePO vehiclePO,
            @RequestParam(value = "file") List<MultipartFile> files
    ) {
        VehicleDTO savedVehicle = vehicleService.saveVehicle(vehicleService.convertPOToDto(vehiclePO), files);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(vehicleDTOAssembler.toModel(vehicleMapper.toEntity(savedVehicle)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleDTO> updateVehicle(
            @Validated(Update.class) @ModelAttribute VehiclePO vehiclePO,
            @RequestParam(value = "file", required = false) List<MultipartFile> files,
            @PathVariable Long id
    ) {
        VehicleDTO updatedVehicle = vehicleService.updateVehicle(vehicleService.convertPOToDto(vehiclePO), files, id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(vehicleDTOAssembler.toModel(vehicleMapper.toEntity(updatedVehicle)));
    }

    @GetMapping
    public ResponseEntity<List<VehicleDTO>> fetchAll() {
        List<VehicleDTO> vehicleList = vehicleService
                .fetchAll()
                .stream()
                .map(vehicleDTO -> vehicleDTOAssembler.toModel(vehicleMapper.toEntity(vehicleDTO)))
                .toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(vehicleList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDTO> fetchById(@PathVariable Long id) {
        VehicleDTO existingVehicle = vehicleService.fetchById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(vehicleDTOAssembler.toModel(vehicleMapper.toEntity(existingVehicle)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<VehicleDTO> setVehicleStatus(
            @RequestBody VehicleStatus status,
            @PathVariable Long id
    ) {
        VehicleDTO updatedVehicle = vehicleService.setVehicleStatus(id, status);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(vehicleDTOAssembler.toModel(vehicleMapper.toEntity(updatedVehicle)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteVehicle(@PathVariable Long id) {
        ApiResponse response = vehicleService.deleteVehicle(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

}
