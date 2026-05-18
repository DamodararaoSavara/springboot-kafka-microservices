package com.microservices.api_gateway.jwt;


import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Component
public class JwtAuthenticationFilterGateWay implements GlobalFilter, Ordered {
    private final JwtTokenProvider jwtTokenProvider;

    public JwtAuthenticationFilterGateWay(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
        System.out.println("JWT FILTER CREATED...............");
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("Loading...............");
        String path = exchange.getRequest().getURI().getPath();
        if (path.startsWith("/api/auth")) {
            return chain.filter(exchange);
        }
        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return onError(exchange, "Missing or Invalid Authorization Header");
        }
        String token = authHeader.substring(7);

        if(!jwtTokenProvider.validateToken(token)){
            return onError(exchange, "Invalid or Expired Token");
        }
        return chain.filter(exchange);
    }
    private Mono<Void> onError(ServerWebExchange exchange, String message) {

        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        exchange.getResponse().getHeaders()
                .setContentType(MediaType.APPLICATION_JSON);

        String body = "{"
                + "\"timestamp\":\"" + LocalDateTime.now() + "\","
                + "\"status\":" + HttpStatus.UNAUTHORIZED.value() + ","
                + "\"error\":\"" + HttpStatus.UNAUTHORIZED.getReasonPhrase()+ "\","
                + "\"message\":\"" + message + "\","
                + "\"path\":\"" + exchange.getRequest().getPath() + "\""
                + "}";

        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);

        return exchange.getResponse()
                .writeWith(Mono.just(exchange.getResponse()
                        .bufferFactory()
                        .wrap(bytes)));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
