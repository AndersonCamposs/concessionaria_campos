package com.example.concessionaria_campos.controller;

import com.example.concessionaria_campos.dto.SaleDTO;
import com.example.concessionaria_campos.service.ReportService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/confirmation-purchase")
    public ResponseEntity<byte[]> confirmationPurchase(@RequestBody SaleDTO saleDTO) {
        try {
            byte[] pdf = reportService.genPurchaseConfirmationReport(saleDTO);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF); // Define o tipo correto
            headers.setContentDisposition(ContentDisposition.attachment().filename("confirmacao-compra.pdf").build()); // Força o download

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .headers(headers)
                    .body(pdf);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar relatório");
        }
    }
}
