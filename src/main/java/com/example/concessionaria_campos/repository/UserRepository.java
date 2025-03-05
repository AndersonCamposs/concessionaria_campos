package com.example.concessionaria_campos.repository;

import com.example.concessionaria_campos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<UserDetails> findByLogin(String login);
}
