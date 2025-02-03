package com.example.concessionaria_campos.controller;

import com.example.concessionaria_campos.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/profile")
    public ResponseEntity<Map<String, Object>> fetchUserProfile(@AuthenticationPrincipal User user) {
        if (user == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Usuário não autenticado"));
        }

        Map<String, Object> userData = new LinkedHashMap<>();
        userData.put("id", user.getId());
        userData.put("login", user.getLogin());
        userData.put("role", user.getRole());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userData);
    }
}
