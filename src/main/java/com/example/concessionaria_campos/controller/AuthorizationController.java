package com.example.concessionaria_campos.controller;

import com.example.concessionaria_campos.dto.AuthenticationDTO;
import com.example.concessionaria_campos.dto.RegisterDTO;
import com.example.concessionaria_campos.entity.User;
import com.example.concessionaria_campos.service.JwtService;
import com.example.concessionaria_campos.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthorizationController {

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final JwtService jwtService;

    public AuthorizationController(AuthenticationManager authenticationManager, UserService userService, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody @Valid AuthenticationDTO authenticationDTO) {
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(
                    authenticationDTO.getLogin(),
                    authenticationDTO.getPassword()
                );

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        String token = jwtService.generateToken((User) authentication.getPrincipal());

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("token", token);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody @Valid RegisterDTO registerDTO) {
        User savedUser = userService.saveUser(registerDTO);

        Map<String, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("id", savedUser.getId());
        linkedHashMap.put("login", savedUser.getLogin());
        linkedHashMap.put("password", savedUser.getPassword());
        linkedHashMap.put("role", savedUser.getRole());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(linkedHashMap);

    }
}
