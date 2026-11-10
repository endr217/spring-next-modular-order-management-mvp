package com.example.ordermanagement.modules.auth.api.response;

public record UserLoginResponse(String accessToken, String tokenType, long expiresIn) {}
