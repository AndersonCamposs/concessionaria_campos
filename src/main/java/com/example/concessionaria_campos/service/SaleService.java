package com.example.concessionaria_campos.service;

import com.example.concessionaria_campos.dto.SaleDTO;
import com.example.concessionaria_campos.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleService {
    @Autowired
    SaleRepository saleRepository;

    public SaleDTO saveSale(SaleDTO sale) {

        return new SaleDTO();
    }
}
