package com.example.concessionaria_campos.dto;

import com.example.concessionaria_campos.entity.Vehicle;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhotoDTO {
    private Long id;

    private String name;

    private String path;

    private Vehicle vehicle;

}