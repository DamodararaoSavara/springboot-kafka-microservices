package com.damodararao.microservice.dto;

public record LoginRequest(String usernameOrEmail,
                           String password) {
}
