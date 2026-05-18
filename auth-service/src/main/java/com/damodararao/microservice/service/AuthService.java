package com.damodararao.microservice.service;

import com.damodararao.microservice.dto.AuthResponse;
import com.damodararao.microservice.dto.LoginRequest;
import com.damodararao.microservice.dto.RegisterRequest;
import com.damodararao.microservice.dto.UserData;

import java.util.List;

public interface AuthService {
    String register(RegisterRequest registerRequest);
    AuthResponse login(LoginRequest loginRequest);
    List<UserData> userInfo();

}
