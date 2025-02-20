package com.example.concessionaria_campos.service;

import com.example.concessionaria_campos.dto.SaleDTO;
import net.sf.jasperreports.engine.*;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

@Service
public class ReportService {

    public byte[] genPurchaseConfirmationReport(SaleDTO saleDTO) {
        try {
            InputStream templateStream = getClass().getResourceAsStream("/reports/RelatorioCompra.jasper");

            HashMap<String, Object> parameters = genParameters(saleDTO);

            JasperPrint jasperPrint = JasperFillManager.fillReport(templateStream, parameters, new JREmptyDataSource());

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

            return outputStream.toByteArray();
        } catch (JRException e) {
            throw new RuntimeException("Erro ao gerar relatório de confirmação da compra.");
        }
    }

    public byte[] genPurchaseCancellationReport(SaleDTO saleDTO) {
        try {
            InputStream templateStream = getClass().getResourceAsStream("/reports/RelatorioCancelamentoCompra.jasper");

            HashMap<String, Object> parameters = genParameters(saleDTO);

            JasperPrint jasperPrint = JasperFillManager.fillReport(templateStream, parameters, new JREmptyDataSource());

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

            return outputStream.toByteArray();
        } catch (JRException e) {
            throw new RuntimeException("Erro ao gerar relatório de cancelamento da compra.");
        }

    }

    private HashMap<String, Object> genParameters(SaleDTO saleDTO) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("nomeCliente", saleDTO.getCustomer().getName());
        parameters.put("cpfCliente", saleDTO.getCustomer().getCpf());
        parameters.put("numPedido", saleDTO.getId());
        parameters.put("dataPedido", Date.from(saleDTO.getDate().atZone(ZoneId.systemDefault()).toInstant()));
        parameters.put("modeloVeiculo", saleDTO.getVehicle().getModel());
        parameters.put("marcaVeiculo", saleDTO.getVehicle().getBrand().getName());
        parameters.put("anoVeiculo", saleDTO.getVehicle().getYear());
        parameters.put("valorVeiculo", saleDTO.getVehicle().getValue());

        return parameters;
    }
}
