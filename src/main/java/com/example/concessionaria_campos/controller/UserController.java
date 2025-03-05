package com.example.concessionaria_campos.controller;

import com.example.concessionaria_campos.assembler.UserDTOAssembler;
import com.example.concessionaria_campos.dto.UserDTO;
import com.example.concessionaria_campos.entity.User;
import com.example.concessionaria_campos.mapper.UserMapper;
import com.example.concessionaria_campos.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final UserDTOAssembler userDTOAssembler;

    public UserController(UserService userService, UserMapper userMapper, UserDTOAssembler userDTOAssembler) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.userDTOAssembler = userDTOAssembler;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable UUID id,
            @RequestParam(value = "login", required = false) String login,
            @RequestParam(value = "password", required = false) String password
    ) {
         UserDTO userUpdated = new UserDTO();
        if (login != null) {
            userUpdated = userService.updateUserLogin(id, login);
        }
        if (password != null) {
            userUpdated = userService.updateUserPassword(id, password);
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userDTOAssembler.toModel(userMapper.toEntity(userUpdated)));

    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> fetchAllUsers() {
        List<UserDTO> userList = userService.fetchAllUsers();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userList);
    }

    @GetMapping("/profile")
    public ResponseEntity<UserDTO> fetchUserProfile(@AuthenticationPrincipal User user) {
        if (user == null) {
            throw new RuntimeException("Usuário não autenticado");
        }


        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userDTOAssembler.toModel(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> fetchById(@PathVariable("id") UUID id) {
        UserDTO existingUser = userService.fetchById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userDTOAssembler.toModel(userMapper.toEntity(existingUser)));
    }

    @GetMapping("/search/{login}")
    public ResponseEntity<UserDTO> fetchByLogin(@PathVariable("login") String login) {
        UserDTO existingUser = userService.fetchByLogin(login);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userDTOAssembler.toModel(userMapper.toEntity(existingUser)));
    }
}
