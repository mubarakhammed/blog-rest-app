package com.mubarak.blogrestapi.service;

import com.mubarak.blogrestapi.model.ApiResponse;
import com.mubarak.blogrestapi.model.User;
import com.mubarak.blogrestapi.payload.LoginDto;
import com.mubarak.blogrestapi.payload.LoginResponse;
import com.mubarak.blogrestapi.payload.RegisterDto;

public interface AuthService {
    LoginResponse login(LoginDto loginDto);
    User register(RegisterDto registerDto);
    ApiResponse confirmToken(String token);
}
