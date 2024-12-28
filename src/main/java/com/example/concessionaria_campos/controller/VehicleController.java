package com.example.concessionaria_campos.controller;

import com.example.concessionaria_campos.dto.Create;
import com.example.concessionaria_campos.dto.Update;
import com.example.concessionaria_campos.dto.VehicleDTO;
import com.example.concessionaria_campos.param.VehiclePO;
import com.example.concessionaria_campos.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping
    public ResponseEntity<VehicleDTO> saveVehicle(
            @Validated(Create.class) @ModelAttribute VehiclePO vehiclePO,
            @RequestParam(value = "file", required = false) List<MultipartFile> files
            ) {
        VehicleDTO savedVehicle = vehicleService.saveVehicle(vehicleService.convertPOToDto(vehiclePO), files);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedVehicle);
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
                .body(updatedVehicle);
    }
}
