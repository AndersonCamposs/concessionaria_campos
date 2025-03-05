package com.example.concessionaria_campos.repository;

import com.example.concessionaria_campos.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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

}
