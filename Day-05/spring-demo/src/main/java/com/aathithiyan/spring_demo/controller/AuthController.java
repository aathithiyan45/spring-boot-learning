package com.aathithiyan.spring_demo.controller;

import com.aathithiyan.spring_demo.dto.LoginRequestDTO;
import com.aathithiyan.spring_demo.dto.LoginResponseDTO;
import com.aathithiyan.spring_demo.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody LoginRequestDTO dto) {

        String token = userService.login(
                dto.getUsername(),
                dto.getPassword()
        );

        return ResponseEntity.ok(
                new LoginResponseDTO(token)
        );
    }
}