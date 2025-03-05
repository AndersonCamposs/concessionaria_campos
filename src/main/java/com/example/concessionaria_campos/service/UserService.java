package com.example.concessionaria_campos.service;

import com.example.concessionaria_campos.dto.RegisterDTO;
import com.example.concessionaria_campos.dto.UserDTO;
import com.example.concessionaria_campos.entity.User;
import com.example.concessionaria_campos.exception.ResourceAlreadyExists;
import com.example.concessionaria_campos.exception.ResourceNotFoundException;
import com.example.concessionaria_campos.mapper.UserMapper;
import com.example.concessionaria_campos.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    public UserDTO saveUser(RegisterDTO registerDTO) {
        Optional<UserDetails> userDetailsOptional = userRepository.findByLogin(registerDTO.getLogin());

        if (userDetailsOptional.isPresent()) {
            throw new ResourceAlreadyExists("Já existe um usuário registrado com este login.");
        }

        String encryptedPassword = bCryptPasswordEncoder.encode(registerDTO.getPassword());


        User user = new User(registerDTO.getLogin(), encryptedPassword, registerDTO.getRole());
        User savedUser = userRepository.save(user);

        return userMapper.toDTO(savedUser);
    }

    public UserDTO updateUserLogin(UUID id, String login) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        existingUser.setLogin(login);

        User updatedUser = userRepository.save(existingUser);
        return userMapper.toDTO(updatedUser);
    }

    public UserDTO updateUserPassword(UUID id, String password) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        existingUser.setPassword(bCryptPasswordEncoder.encode(password));
        User updatedUser = userRepository.save(existingUser);

        return userMapper.toDTO(updatedUser);
    }

    public List<UserDTO> fetchAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(userMapper::toDTO)
                .toList();
    }

    public UserDTO fetchById(UUID id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        return userMapper.toDTO(existingUser);
    }

    public UserDTO fetchByLogin(String login) {
        UserDetails user = userRepository.findByLogin(login)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        return userMapper.toDTO((User) user);
    }

    public boolean validatePassword(User user, String password) {
        boolean matches = bCryptPasswordEncoder.matches(password, user.getPassword());
        if (!matches) {
            throw new RuntimeException("Senha incorreta");
        }

        return true;
    }

}
