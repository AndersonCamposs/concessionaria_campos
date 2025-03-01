package com.example.concessionaria_campos.controller;

import com.example.concessionaria_campos.entity.User;
import com.example.concessionaria_campos.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = userService.fetchAllUsers();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userList);
    }

    @GetMapping("/profile")
    public ResponseEntity<Map<String, Object>> fetchUserProfile(@AuthenticationPrincipal User user) {
        if (user == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Usuário não autenticado"));
        }

        Map<String, Object> userData = prepareUserResponse(user);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> fetchById(@PathVariable("id") Long id) {
        User existingUser = userService.fetchById(id);

        Map<String, Object> userData = prepareUserResponse(existingUser);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userData);

    }

    private Map<String, Object> prepareUserResponse(User user) {
        Map<String, Object> userData = new LinkedHashMap<>();
        userData.put("id", user.getId());
        userData.put("login", user.getLogin());
        userData.put("role", user.getRole());

        return userData;
    }
}
