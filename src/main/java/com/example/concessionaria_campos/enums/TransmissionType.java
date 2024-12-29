package com.example.concessionaria_campos.enums;

import lombok.Getter;

@Getter
public enum TransmissionType {
    MANUAL("Manual"),
    AUTOMATIC("Automático");

    private String transmission;

    TransmissionType(String transmission) {
        this.transmission = transmission;
    }
}
