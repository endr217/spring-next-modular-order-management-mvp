package com.example.ordermanagement.modules.auth.service;

import com.example.ordermanagement.modules.auth.api.request.UserLoginRequest;
import com.example.ordermanagement.modules.auth.api.response.UserLoginResponse;

public interface AuthService {
  UserLoginResponse login(UserLoginRequest request);
}
