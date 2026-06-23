package com.debanuj.trackflow.controller;

import com.debanuj.trackflow.dto.LoginRequest;
import com.debanuj.trackflow.dto.LoginResponse;
import com.debanuj.trackflow.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(
            AuthService authService
    ) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request
    ) {
        return authService.login(request);
    }
}