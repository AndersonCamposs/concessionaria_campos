package com.example.concessionaria_campos.repository;

import com.example.concessionaria_campos.dto.statistic.MonthlyUserSaleAmount;
import com.example.concessionaria_campos.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StatisticRepository {

    @PersistenceContext
    EntityManager em;

    public Integer findUserSales(User user) {
        String jpql = "select count(s) from Sale s where s.user = :user";

        return em.createQuery(jpql, Long.class)
                .setParameter("user", user)
                .getSingleResult()
                .intValue();
    }

    public List<MonthlyUserSaleAmount> findMonthlyUserSales(User user, int year) {
        String jpql = "select YEAR(s.date) as year, MONTH(s.date) as month, SUM(s.value) as amount" +
                        " from Sale s where s.user = :user and YEAR(s.date) = :year" +
                        " group by YEAR(s.date), MONTH(s.date)";

        List<Object[]> result = em.createQuery(jpql, Object[].class)
                .setParameter("user", user)
                .setParameter("year", year)
                .getResultList();

        return result
                .stream()
                .map(row -> new MonthlyUserSaleAmount(
                        Year.of(((Number) row[0]).intValue()),
                        Month.of(((Number) row[1]).intValue()),
                        BigDecimal.valueOf(((Number)row[2]).doubleValue())
                ))
                .collect(Collectors.toList());

    }

}
