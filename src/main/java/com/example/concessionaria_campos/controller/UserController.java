package com.example.concessionaria_campos.controller;

import com.example.concessionaria_campos.entity.User;
import com.example.concessionaria_campos.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

    @PatchMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateUser(
            @PathVariable Long id,
            @RequestParam(value = "login", required = false) String login,
            @RequestParam(value = "password", required = false) String password
    ) {
         User userUpdated = new User();
        if (login != null) {
            userUpdated = userService.updateUserLogin(id, login);
        }
        if (password != null) {
            userUpdated = userService.updateUserPassword(id, password);
        }

        Map<String, Object> userData =prepareUserResponse(userUpdated);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userData);

    }

    @GetMapping
    public ResponseEntity<List<User>> fetchAllUsers() {
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

    @GetMapping("/search/{login}")
    public ResponseEntity<Map<String, Object>> fetchByLogin(@PathVariable("login") String login) {
        User existingUser = userService.fetchByLogin(login);
        Map<String, Object> userData = prepareUserResponse(existingUser);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userData);
    }

    private Map<String, Object> prepareUserResponse(User user) {
        Map<String, Object> userData = new LinkedHashMap<>();
        userData.put("id", user.getId());
        userData.put("login", user.getLogin());
        userData.put("password", user.getPassword());
        userData.put("role", user.getRole());

        return userData;
    }
}
