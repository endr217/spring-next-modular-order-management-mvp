package com.example.ordermanagement.modules.auth.service.impl;

import com.example.ordermanagement.modules.auth.api.exception.InvalidCredentialsException;
import com.example.ordermanagement.modules.auth.api.request.UserLoginRequest;
import com.example.ordermanagement.modules.auth.api.response.UserLoginResponse;
import com.example.ordermanagement.modules.auth.service.AuthService;
import com.example.ordermanagement.modules.auth.service.TokenService;
import com.example.ordermanagement.modules.users.domain.User;
import com.example.ordermanagement.modules.users.infrastructure.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthServiceImpl implements AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final TokenService tokenService;

  public AuthServiceImpl(
      UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.tokenService = tokenService;
  }

  @Override
  @Transactional(readOnly = true)
  public UserLoginResponse login(UserLoginRequest request) {
    User user =
        userRepository
            .findByUsername(request.username().trim())
            .filter(u -> passwordEncoder.matches(request.password(), u.getPasswordHash()))
            .orElseThrow(InvalidCredentialsException::new);
    return tokenService.issue(user);
  }
}
