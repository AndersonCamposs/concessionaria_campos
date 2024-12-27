package com.example.concessionaria_campos.controller;

import com.example.concessionaria_campos.dto.Create;
import com.example.concessionaria_campos.dto.VehicleDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @PostMapping
    public ResponseEntity<VehicleDTO> saveVehicle(@RequestBody @Validated(Create.class) VehicleDTO data) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(data);
    }
}
