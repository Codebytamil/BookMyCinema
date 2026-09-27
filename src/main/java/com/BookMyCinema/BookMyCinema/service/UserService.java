package com.BookMyCinema.BookMyCinema.service;

import com.BookMyCinema.BookMyCinema.model.User;
import com.BookMyCinema.BookMyCinema.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get one user by id
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}