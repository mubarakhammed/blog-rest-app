package com.mubarak.blogrestapi.controller;

import com.mubarak.blogrestapi.model.ApiResponse;
import com.mubarak.blogrestapi.model.User;
import com.mubarak.blogrestapi.payload.LoginResponse;
import com.mubarak.blogrestapi.payload.LoginDto;
import com.mubarak.blogrestapi.payload.RegisterDto;
import com.mubarak.blogrestapi.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Build Login REST API
    @PostMapping(value = {"/api/v1/auth/login", "/signin"})
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDto loginDto){
        LoginResponse response = authService.login(loginDto);

        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            // Handle login failure
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    // Build Register REST API
    @PostMapping(value = {"/api/v1/auth/register", "/api/v1/auth//signup"})
    public ResponseEntity<ApiResponse<User>> register(@RequestBody RegisterDto registerDto){
        ApiResponse<User> response = new ApiResponse<>();
        User user = authService.register(registerDto);
        if (response != null) {
            response.setStatus(true);
            response.setData(user);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            response.setStatus(false);
            response.setErrorMessage("Registration failed"); // Set an appropriate error message
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/api/v1/auth/verify")
    public ResponseEntity<ApiResponse<String>>  verify(@RequestParam("token") String token) {
        ApiResponse<String> response =authService.confirmToken(token);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
