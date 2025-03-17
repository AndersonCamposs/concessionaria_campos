package com.example.concessionaria_campos.dto.statistic;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Month;
import java.time.Year;

@Data
@AllArgsConstructor
public class MonthlyUserSaleCount {
    private Year year;
    private Month month;
    private Integer count;
}
