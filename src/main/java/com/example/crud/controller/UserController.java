package com.example.crud.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.DTO.UserDTO;
import com.example.crud.model.ApiResponse;
import com.example.crud.model.User;
import com.example.crud.service.UserService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userServices;

    public UserController(UserService userServices) {
        this.userServices = userServices;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserDTO>>> getAllUsers() {
        List<UserDTO> userDTO = userServices.getAllUsers();
        return ResponseEntity.ok(new ApiResponse<>(200, true, "List of users", userDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> getUser(@PathVariable int id) {
        UserDTO user = userServices.getUserById(id);
        
        return ResponseEntity.ok(new ApiResponse<>(200, true, "User found", user));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserDTO>> create(@Valid @RequestBody User user) {
        UserDTO newUser = userServices.createUser(user);
        return ResponseEntity.ok(new ApiResponse<>(201, true, "User created", newUser));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> delete(@PathVariable int id) {
        UserDTO user = userServices.deleteUser(id);
        return ResponseEntity.ok(new ApiResponse<>(200, true, "User deleted", user));
    }
}
