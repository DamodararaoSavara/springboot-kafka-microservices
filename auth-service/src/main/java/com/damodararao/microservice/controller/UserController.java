package com.damodararao.microservice.controller;

import com.damodararao.microservice.dto.LoginRequest;
import com.damodararao.microservice.dto.UserData;
import com.damodararao.microservice.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
   private final AuthService authService;

    public UserController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public ResponseEntity<List<UserData>> userData(){
        List<UserData> userData = authService.userInfo();
        return new ResponseEntity<>(userData, HttpStatus.OK);
    }

}
