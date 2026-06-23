package com.debanuj.trackflow.service;

import com.debanuj.trackflow.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.debanuj.trackflow.dto.LoginRequest;
import com.debanuj.trackflow.dto.LoginResponse;
import com.debanuj.trackflow.entity.User;
import com.debanuj.trackflow.exception.InvalidCredentialsException;

import java.util.Optional;

@Service
public class AuthService {
    public LoginResponse login(
            LoginRequest request
    ) {

        Optional<User> userOptional =
                userRepository.findByEmail(
                        request.getEmail()
                );

        if (userOptional.isEmpty()) {
            throw new InvalidCredentialsException(
                    "Invalid email or password"
            );
        }

        User user = userOptional.get();

        boolean passwordMatches =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPasswordHash()
                );

        if (!passwordMatches) {
            throw new InvalidCredentialsException(
                    "Invalid email or password"
            );
        }

        return new LoginResponse(
                "LOGIN_SUCCESS"
        );
    }
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
}