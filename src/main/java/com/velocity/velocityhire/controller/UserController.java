package com.velocity.velocityhire.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.velocity.velocityhire.dto.ApiResponse;
import com.velocity.velocityhire.dto.UserCreateDTO;
import com.velocity.velocityhire.dto.UserDTO;
import com.velocity.velocityhire.dto.UserUpdateDTO;
import com.velocity.velocityhire.entity.User;
import com.velocity.velocityhire.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserDTO>>> getUsers() {
        List<User> users = userService.findAll();
        List<UserDTO> userDTOs = users.stream()
            .map(user -> new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail()))
            .toList();

        ApiResponse<List<UserDTO>> response = new ApiResponse<>(
            userDTOs,
            "Users fetched successfully"
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> findUser(@PathVariable("id") long id) {
        User user = userService.find(id);
        UserDTO userDTO = UserDTO.builder()
            .id(user.getId())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .email(user.getEmail())
            .build();

        ApiResponse<UserDTO> response = new ApiResponse<>(userDTO, "User found successfully");
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserDTO>> createUser(@RequestBody UserCreateDTO userCreateDTO) {
        User user = User.builder()
            .firstName(userCreateDTO.getFirstName())
            .lastName(userCreateDTO.getLastName())
            .email(userCreateDTO.getEmail())
            .build();
        User savedUser = userService.register(user, userCreateDTO.getPassword());

        UserDTO userDTO = new UserDTO(savedUser.getId(), savedUser.getFirstName(), savedUser.getLastName(), savedUser.getEmail());
        ApiResponse<UserDTO> response = new ApiResponse<>(userDTO, "User created successfully");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") long id, @RequestBody UserUpdateDTO userUpdateDTO) {
        boolean updated = userService.update(id, userUpdateDTO);
        if (!updated) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("User updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id) {
        boolean userDeleted = userService.delete(id);
        if (!userDeleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("User deleted successfully");
    }
}