package com.velocity.velocityhire.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class UserController {
    @GetMapping("/user")
    public String index() {
        return "User index";
    }

    @GetMapping("/user/{id}")
    public String getUser(@PathVariable("id") int id) {
        return String.format("Loading for user %d", id);
    }

    @PostMapping("/user")
    public String createUser() {
        return "create user";
    }

    @PutMapping("/user/{id}")
    public String updateUser(@PathVariable("id") int id) {
        return "update user";
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        return "delete user";
    }
}