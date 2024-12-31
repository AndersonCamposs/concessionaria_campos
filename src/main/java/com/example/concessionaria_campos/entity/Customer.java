package com.example.concessionaria_campos.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name="customer")
@Getter
@Setter
public class Customer {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "email", nullable = true, unique = true)
    private String email;

    @Column(name = "born_Date", nullable = false)
    private LocalDate bornDate;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Sale> purchases;
}

