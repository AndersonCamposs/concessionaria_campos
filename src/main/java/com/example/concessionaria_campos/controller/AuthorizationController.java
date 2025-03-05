package com.example.concessionaria_campos.controller;

import com.example.concessionaria_campos.assembler.UserDTOAssembler;
import com.example.concessionaria_campos.dto.AuthenticationDTO;
import com.example.concessionaria_campos.dto.RegisterDTO;
import com.example.concessionaria_campos.dto.UserDTO;
import com.example.concessionaria_campos.entity.User;
import com.example.concessionaria_campos.mapper.UserMapper;
import com.example.concessionaria_campos.service.JwtService;
import com.example.concessionaria_campos.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthorizationController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtService jwtService;
    private final UserDTOAssembler userDTOAssembler;
    private final UserMapper userMapper;

    public AuthorizationController(AuthenticationManager authenticationManager, UserService userService, JwtService jwtService, UserDTOAssembler userDTOAssembler, UserMapper userMapper) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtService = jwtService;
        this.userDTOAssembler = userDTOAssembler;
        this.userMapper = userMapper;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(
        @RequestBody @Valid AuthenticationDTO authenticationDTO,
        HttpServletResponse response
    ) {
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(
                    authenticationDTO.getLogin(),
                    authenticationDTO.getPassword()
                );

        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        User userAuthenticated = (User) authentication.getPrincipal();

        String token = jwtService.generateToken((User) authentication.getPrincipal());
        jwtService.addTokenToCookie(response, token);

        Map<String, Object> responseObject = new LinkedHashMap<>();
        responseObject.put("token", token);
        responseObject.put("user", userMapper.toDTO(userAuthenticated));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseObject);
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, Object>> logout(HttpServletResponse response) {
        jwtService.removeTokenCookie(response);

        Map<String, Object> responseObject = new LinkedHashMap<>();
        responseObject.put("message", "Logout bem sucedido");

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseObject);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody @Valid RegisterDTO registerDTO) {
        UserDTO savedUser = userService.saveUser(registerDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userDTOAssembler.toModel(userMapper.toEntity(savedUser)));

    }

    @PostMapping("/verify-password")
    public ResponseEntity<Map<String, Object>> verifyPassword(
            @AuthenticationPrincipal User user,
            @RequestParam(value = "password") String password
    ) {
        userService.validatePassword(user, password);
        Map<String, Object> responseObject = new LinkedHashMap<>();
        responseObject.put("message", "Senha validada com sucesso.");

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseObject);
    }
}
