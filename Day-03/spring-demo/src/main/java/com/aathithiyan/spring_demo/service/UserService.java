package com.aathithiyan.spring_demo.service;

import com.aathithiyan.spring_demo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void createUser() {
        repository.saveUser();
    }
}