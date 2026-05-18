package com.damodararao.microservice.dto;

import java.util.List;

public record AuthResponse(String token,
                           Long userId,
                           List<String> roles) {
}
