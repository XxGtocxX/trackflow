package com.debanuj.trackflow.controller;

import com.debanuj.trackflow.dto.CreateUserRequest;
import com.debanuj.trackflow.entity.User;
import com.debanuj.trackflow.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(
            @Valid @RequestBody CreateUserRequest request
    ) {
        return userService.createUser(request);
    }
}