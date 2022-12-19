package com.example.petstore.services;

import com.example.petstore.model.User;
import com.example.petstore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public User getUserById(Integer id) {
        return userRepository.findUserById(id);
    }

    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public User updateUser(User target, User source) {
        target.setUsername(source.getUsername());
        target.setFirstname(source.getFirstname());
        target.setLastname(source.getLastname());
        target.setEmail(source.getEmail());
        target.setPassword(source.getPassword());
        target.setPhone(source.getPhone());
        target.setStatus(source.getStatus());

        return saveUser(target);
    }
}
