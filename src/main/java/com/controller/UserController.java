package com.iauro.controller;

import com.iauro.model.User;
import com.iauro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Map;

@RestController
@RequestMapping("/signup")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public String signup(@RequestBody Map<String, String> userDto) {
        User user = new User();
        user.setUsername(userDto.get("username"));
        user.setPassword(passwordEncoder.encode(userDto.get("password")));
        
        String role = userDto.get("role");
        if ("ADMIN".equalsIgnoreCase(role)) {
            user.setRoles(new HashSet<>(Set.of("ADMIN")));
        } else {
            user.setRoles(new HashSet<>(Set.of("CUSTOMER")));
        }

        userRepository.save(user);
        return "User registered successfully";
    }
}
