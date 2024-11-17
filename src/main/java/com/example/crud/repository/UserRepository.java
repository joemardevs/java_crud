package com.example.crud.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.crud.model.User;

import jakarta.annotation.PostConstruct;

@Repository
public class UserRepository  {
    List<User> users = new ArrayList<>();

    public List<User> findAll() {
        return users;
    }

    public Optional<User> findById(int id) {
        return Optional.ofNullable(
                users.stream()
                        .filter(user -> user.getId() == id)
                        .findFirst()
                        .orElse(null));
    }
    
    public User save(User user) {
        users.add(user);
        return user;
    }

    public User delete(User user) {
        Date currentDateTime = new Date();                
        user.setDeletedAt(currentDateTime);
        user.setUpdatedAt(currentDateTime);
        user.setActive(false);
        
        return user;
    }

    @PostConstruct
    public void init() {
        users.add(new User( "John Doe", "johndoe@gmail.com", "password"));
        users.add(new User( "Joemar Palting", "joemarpalting19@gmail.com", "password"));
        users.add(new User( "Test User", "test@gmail.com", "password"));
    };
}
