package com.example.concessionaria_campos.controller;

import com.example.concessionaria_campos.dto.AuthenticationDTO;
import com.example.concessionaria_campos.dto.RegisterDTO;
import com.example.concessionaria_campos.entity.User;
import com.example.concessionaria_campos.service.AuthorizationService;
import com.example.concessionaria_campos.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthorizarionController {

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    public AuthorizarionController(AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid AuthenticationDTO authenticationDTO) {
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(
                    authenticationDTO.getLogin(),
                    authenticationDTO.getPassword()
                );

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        return ResponseEntity.ok().body("Logado");
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @Valid RegisterDTO registerDTO) {
        User savedUser = userService.saveUser(registerDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedUser);

    }
}
