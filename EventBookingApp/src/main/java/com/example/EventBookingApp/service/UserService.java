package com.example.EventBookingApp.service;

import com.example.EventBookingApp.model.App;
import com.example.EventBookingApp.model.Role;
import com.example.EventBookingApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public App registerUser(String username, String password, Role role) {
        App user = new App();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        return userRepository.save(user);
    }

    public Optional<App> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
