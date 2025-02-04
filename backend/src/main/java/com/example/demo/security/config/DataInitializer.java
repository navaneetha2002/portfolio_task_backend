package com.example.demo.security.config;

import com.example.demo.security.entity.User;
import com.example.demo.security.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) {
        if (userRepository.count() == 0) {
            User user = new User();
            user.setId(UUID.randomUUID()); // Ensure UUID generation
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("password"));
            if (userRepository.findByUsername(user.getUsername()) == null) {
                userRepository.save(user);
            }
        }
    }
}
