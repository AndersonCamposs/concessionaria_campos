package com.example.concessionaria_campos.service;

import com.example.concessionaria_campos.dto.UserDTO;
import com.example.concessionaria_campos.dto.statistic.MonthlyUserSaleAmount;
import com.example.concessionaria_campos.entity.User;
import com.example.concessionaria_campos.mapper.UserMapper;
import com.example.concessionaria_campos.repository.StatisticRepository;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticService {

    private final StatisticRepository statisticRepository;
    private final UserMapper userMapper;

    public StatisticService(StatisticRepository statisticRepository, UserMapper userMapper) {
        this.statisticRepository = statisticRepository;
        this.userMapper = userMapper;
    }

    public Integer countUserSales(UserDTO user) {
        Integer count = statisticRepository.findUserSales(userMapper.toEntity(user));
        return count;
    }

    public List<MonthlyUserSaleAmount> sumMonthlyUserSales(UserDTO user) {
        int currentYear = Year.now().getValue();
        List<MonthlyUserSaleAmount> monthlyUserSaleAmount = statisticRepository
                .findMonthlyUserSales(userMapper.toEntity(user), currentYear);

        return monthlyUserSaleAmount;
    }
}
