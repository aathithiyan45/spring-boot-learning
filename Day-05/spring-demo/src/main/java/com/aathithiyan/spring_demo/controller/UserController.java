package com.aathithiyan.spring_demo.controller;

import com.aathithiyan.spring_demo.dto.UserRequestDTO;
import com.aathithiyan.spring_demo.model.User;
import com.aathithiyan.spring_demo.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(
            @RequestBody UserRequestDTO dto) {

        User user = userService.registerUser(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(user);
    }
}