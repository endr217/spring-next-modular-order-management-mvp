package com.example.ordermanagement.modules.auth.api.controller;

import com.example.ordermanagement.modules.auth.api.request.UserLoginRequest;
import com.example.ordermanagement.modules.auth.api.response.UserLoginResponse;
import com.example.ordermanagement.modules.auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/login")
  public ResponseEntity<UserLoginResponse> login(@Valid @RequestBody UserLoginRequest request) {
    return ResponseEntity.ok(authService.login(request));
  }
}
