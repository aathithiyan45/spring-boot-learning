package com.aathithiyan.spring_demo.controller;

import com.aathithiyan.spring_demo.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String createUser() {

        userService.createUser();

        return "User created successfully";
    }
}