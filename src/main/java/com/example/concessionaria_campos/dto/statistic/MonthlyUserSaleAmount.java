package com.example.concessionaria_campos.dto.statistic;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Month;
import java.time.Year;

@Data
@AllArgsConstructor
public class MonthlyUserSaleAmount {
    private Year year;
    private Month month;
    private BigDecimal amount;
}
