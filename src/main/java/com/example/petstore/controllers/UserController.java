package com.example.petstore.controllers;

import com.example.petstore.model.User;
import com.example.petstore.services.UserService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable(value = "username") String username) {
        if (Strings.isBlank(username)) {
            return ResponseEntity.badRequest().build();
        }

        User user = userService.getUserByUsername(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }

    @PutMapping("/user/{username}")
    public ResponseEntity<User> updateUserByUsername(@PathVariable(value = "username") String username,
                                                     @RequestBody User data) {
        if (Strings.isBlank(username)) {
            return ResponseEntity.badRequest().build();
        }

        User user = userService.getUserByUsername(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userService.updateUser(user, data));
    }

    @DeleteMapping("/user/{username}")
    public ResponseEntity<User> deleteUserByUsername(@PathVariable(value = "username") String username) {
        if (Strings.isBlank(username)) {
            return ResponseEntity.badRequest().build();
        }

        User user = userService.getUserByUsername(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        userService.deleteUser(user);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUserByUsername(@RequestBody User user) {
        userService.saveUser(user);

        return ResponseEntity.ok().build();
    }
}
