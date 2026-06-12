package com.debanuj.trackflow.service;

import com.debanuj.trackflow.dto.CreateUserRequest;
import com.debanuj.trackflow.entity.User;
import com.debanuj.trackflow.enums.Role;
import com.debanuj.trackflow.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(CreateUserRequest request) {

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        // Hash password before saving
        user.setPasswordHash(
                passwordEncoder.encode(request.getPassword())
        );

        // Default role assigned by server
        user.setRole(Role.DEVELOPER);

        return userRepository.save(user);
    }
}