package com.example.concessionaria_campos.service;

import com.example.concessionaria_campos.dto.RegisterDTO;
import com.example.concessionaria_campos.entity.User;
import com.example.concessionaria_campos.exception.ResourceAlreadyExists;
import com.example.concessionaria_campos.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = passwordEncoder;
    }

    public User saveUser(RegisterDTO registerDTO) {
        Optional<UserDetails> userDetailsOptional = userRepository.findByLogin(registerDTO.getLogin());

        if (userDetailsOptional.isPresent()) {
            throw new ResourceAlreadyExists("Já existe um usuário registrado com este login.");
        }

        String encryptedPassword = bCryptPasswordEncoder.encode(registerDTO.getPassword());


        User user = new User(registerDTO.getLogin(), encryptedPassword, registerDTO.getRole());
        User savedUser = userRepository.save(user);

        return savedUser;
    }
}
