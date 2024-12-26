package com.example.concessionaria_campos.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="marca")
@Getter
@Setter
public class Marca {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;


    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "foto", nullable = true)
    private String foto;
}
