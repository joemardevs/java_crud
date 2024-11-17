package com.example.crud.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.crud.exception.user.UserNotFoundException;
import com.example.crud.model.User;

import jakarta.annotation.PostConstruct;

@Repository
public class UserRepository  {
    List<User> users = new ArrayList<>();

    public List<User> findAll() {
        return users;
    }

    public User findById(int id) {
        return users.stream()
                    .filter(user -> user.getId() == id)
                    .findFirst()
                    .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
    }
    
    public User save(User user) {
        users.add(user);
        return user;
    }


    @PostConstruct
    public void init() {
        users.add(new User( "John Doe", "johndoe@gmail.com", "password"));
        users.add(new User( "Joemar Palting", "joemarpalting19@gmail.com", "password"));
        users.add(new User( "Test User", "test@gmail.com", "password"));
    };
}
