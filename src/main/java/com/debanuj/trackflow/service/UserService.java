package com.debanuj.trackflow.service;

import com.debanuj.trackflow.exception.EmailAlreadyExistsException;
import com.debanuj.trackflow.dto.CreateUserRequest;
import com.debanuj.trackflow.dto.UserResponse;
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

    public UserResponse createUser(CreateUserRequest request) {
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException(
                    "Email already exists"
            );
        }
        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        user.setPasswordHash(
                passwordEncoder.encode(request.getPassword())
        );

        user.setRole(Role.DEVELOPER);

        User savedUser = userRepository.save(user);

        UserResponse response = new UserResponse();

        response.setId(savedUser.getId());
        response.setName(savedUser.getName());
        response.setEmail(savedUser.getEmail());
        response.setRole(savedUser.getRole().name());

        return response;
    }
}