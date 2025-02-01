package com.example.concessionaria_campos.service;

import com.example.concessionaria_campos.dto.RegisterDTO;
import com.example.concessionaria_campos.entity.User;
import com.example.concessionaria_campos.exception.RegisterAlreadyExists;
import com.example.concessionaria_campos.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User saveUser(RegisterDTO registerDTO) {
        Optional<UserDetails> userDetailsOptional = userRepository.findByLogin(registerDTO.getLogin());

        if (userDetailsOptional.isPresent()) {
            throw new RegisterAlreadyExists("Já existe um usuário registrado com este login.");
        }

        String encryptedPassword = bCryptPasswordEncoder.encode(registerDTO.getPassword());

        User savedUser = new User(registerDTO.getLogin(), encryptedPassword, registerDTO.getRole());

        return savedUser;
    }
}
