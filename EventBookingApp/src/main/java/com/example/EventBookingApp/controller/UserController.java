package com.example.EventBookingApp.controller;

import com.example.EventBookingApp.model.App;
import com.example.EventBookingApp.service.UserService;
import com.example.EventBookingApp.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public App registerUser(@RequestParam String username, @RequestParam String password, @RequestParam Role role) {
        return userService.registerUser(username, password, role);
    }
}
