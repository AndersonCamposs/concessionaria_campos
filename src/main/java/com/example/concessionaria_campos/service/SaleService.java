package com.example.concessionaria_campos.service;

import com.example.concessionaria_campos.dto.ApiResponse;
import com.example.concessionaria_campos.dto.SaleDTO;
import com.example.concessionaria_campos.entity.Sale;
import com.example.concessionaria_campos.enums.VehicleStatus;
import com.example.concessionaria_campos.exception.ResourceNotFoundException;
import com.example.concessionaria_campos.exception.ResourceUnavailableException;
import com.example.concessionaria_campos.mapper.SaleMapper;
import com.example.concessionaria_campos.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private SaleMapper saleMapper;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private VehicleService vehicleService;

    public SaleDTO saveSale(SaleDTO sale) {
        sale.setCustomer(customerService.fetchById(sale.getCustomer().getId()));
        sale.setVehicle(vehicleService.fetchById(sale.getVehicle().getId()));
        sale.setDate(LocalDateTime.now());
        if (!(sale.getVehicle().getStatus() == VehicleStatus.AVAILABLE)) {
            throw new ResourceUnavailableException("Veículo não disponível para venda");
        }

        Sale savedSale = saleRepository.save(saleMapper.toEntity(sale));
        vehicleService.setVehicleStatus(sale.getVehicle().getId(), VehicleStatus.SOLD);
        return saleMapper.toDTO(savedSale);
    }

    // UPDATE METHOD (NOT ALLOWED)

    public List<SaleDTO> fetchAll() {
        return saleRepository
                .findAll()
                .stream()
                .map(saleMapper::toDTO)
                .toList();
    }

    public SaleDTO fetchById(Long id) {
        Sale existingSale = saleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venda não encontrada"));

        return saleMapper.toDTO(existingSale);
    }

    public ApiResponse deleteSale(Long id) {
        Sale existingSale = saleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venda não encontrada"));
        vehicleService.setVehicleStatus(existingSale.getVehicle().getId(), VehicleStatus.AVAILABLE);
        saleRepository.deleteById(id);
        return new ApiResponse("Venda deletada com sucesso.");
    }
}
