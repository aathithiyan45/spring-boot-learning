package com.aathithiyan.spring_demo.service;

import com.aathithiyan.spring_demo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;
    private final EmailService emailService;

    public UserService(UserRepository repository,
                       EmailService emailService) {
        this.repository = repository;
        this.emailService = emailService;
    }

    public void createUser() {

        repository.saveUser();

        emailService.sendEmail();
    }
}