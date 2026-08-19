package com.aathithiyan.spring_demo.repository;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    public void saveUser() {
        System.out.println("User saved to database");
    }
}