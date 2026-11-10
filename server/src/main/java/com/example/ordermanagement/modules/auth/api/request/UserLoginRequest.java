package com.example.ordermanagement.modules.auth.api.request;

import jakarta.validation.constraints.NotBlank;

public record UserLoginRequest(
    @NotBlank(message = "{auth.username_required}") String username,
    @NotBlank(message = "{auth.password_required}") String password) {}
