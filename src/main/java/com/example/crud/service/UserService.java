package com.example.crud.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import com.example.crud.DTO.UserDTO;
import com.example.crud.model.User;
import com.example.crud.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::convertToUserDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(int id) {
        User user = userRepository.findById(id);
        return convertToUserDTO(user);
    }
    
    public UserDTO createUser(User user) {
        User newUser = userRepository.save(user);
        return convertToUserDTO(newUser);
    }

    public UserDTO updateUser(int id, User user) {
        User existingUser = userRepository.findById(id);
                
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setUpdatedAt(new Date());
                
        return convertToUserDTO(existingUser);
    }
    
    public UserDTO deleteUser(int id) {
        User user = userRepository.findById(id);
                
    Date currentDateTime = new Date();                
        user.setDeletedAt(currentDateTime);
        user.setUpdatedAt(currentDateTime);
        user.setActive(false);

        return convertToUserDTO(user);
    }
    
    private UserDTO convertToUserDTO(User user) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(user, UserDTO.class);
    }
}
