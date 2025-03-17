package com.example.concessionaria_campos.controller;

import com.example.concessionaria_campos.dto.statistic.MonthlyUserSaleAmount;
import com.example.concessionaria_campos.dto.statistic.MonthlyUserSaleCount;
import com.example.concessionaria_campos.entity.User;
import com.example.concessionaria_campos.mapper.UserMapper;
import com.example.concessionaria_campos.service.StatisticService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistic")
public class StatisticController {

    private final StatisticService statisticService;
    private final UserMapper userMapper;

    public StatisticController(StatisticService statisticService, UserMapper userMapper) {
        this.statisticService = statisticService;
        this.userMapper = userMapper;
    }

    @GetMapping("/user/sales/monthly-count")
    public ResponseEntity<List<MonthlyUserSaleCount>> countUserSales(@AuthenticationPrincipal User user) {
        List<MonthlyUserSaleCount> listResult = statisticService.countUserSales(userMapper.toDTO(user));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(listResult);
    }

    @GetMapping("/user/sales/monthly-amount")
    public ResponseEntity<List<MonthlyUserSaleAmount>> fetchMonthlyUserSales(@AuthenticationPrincipal User user) {
        List<MonthlyUserSaleAmount> listResult = statisticService.fetchMonthlyUserSales(userMapper.toDTO(user));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(listResult);
    }
}
