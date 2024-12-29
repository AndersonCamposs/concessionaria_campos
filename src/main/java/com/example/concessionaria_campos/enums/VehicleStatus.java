package com.example.concessionaria_campos.enums;

import lombok.Getter;

@Getter
public enum VehicleStatus {
    AVAILABLE("Disponível"),
    SOLD("Vendido"),
    MAINTENANCE("Manutenção");

    private String status;

    VehicleStatus(String status) {
        this.status = status;
    }
}
