package com.aathithiyan.spring_demo.service;

import com.aathithiyan.spring_demo.dto.UserRequestDTO;
import com.aathithiyan.spring_demo.model.User;
import com.aathithiyan.spring_demo.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(UserRequestDTO dto) {

        User user = new User();

        user.setUsername(dto.getUsername());

        // Plain password → BCrypt hash
        user.setPassword(
                passwordEncoder.encode(dto.getPassword())
        );

        user.setRole(dto.getRole());

        return userRepository.save(user);
    }
}