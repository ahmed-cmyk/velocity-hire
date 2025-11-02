package com.velocity.velocityhire.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.velocity.velocityhire.dto.UserDTO;
import com.velocity.velocityhire.dto.UserUpdateDTO;
import com.velocity.velocityhire.entity.User;
import com.velocity.velocityhire.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(User user, String password) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        user.setPasswordHash(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new EntityNotFoundException("No users found");
        }
        return users;
    }

    public User find(long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User not found with id:" + id));
    }

    public boolean update(long id, UserUpdateDTO userDTO) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (userDTO.getFirstName() != null) {
                user.setFirstName(userDTO.getFirstName());
            }
            if (userDTO.getLastName() != null) {
                user.setLastName(userDTO.getLastName());
            }
            if (userDTO.getEmail() != null) {
                user.setEmail(userDTO.getEmail());
            }

            userRepository.save(user);
            return true;
        }
        return false;
    }

    public boolean delete(long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}