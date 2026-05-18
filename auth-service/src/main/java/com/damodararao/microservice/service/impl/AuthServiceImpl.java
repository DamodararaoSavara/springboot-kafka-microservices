package com.damodararao.microservice.service.impl;

import com.damodararao.microservice.dto.AuthResponse;
import com.damodararao.microservice.dto.LoginRequest;
import com.damodararao.microservice.dto.RegisterRequest;
import com.damodararao.microservice.dto.UserData;
import com.damodararao.microservice.entity.Role;
import com.damodararao.microservice.entity.User;
import com.damodararao.microservice.jwt.JwtTokenProvider;
import com.damodararao.microservice.repository.RoleRepository;
import com.damodararao.microservice.repository.UserRepository;
import com.damodararao.microservice.service.AuthService;
import com.damodararao.microservice.exception.RegisterApiException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String register(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.email())) {
            throw new RegisterApiException(HttpStatus.BAD_REQUEST, "Email already exist");
        }
        if (userRepository.existsByUsername(registerRequest.username())) {
            throw new RegisterApiException(HttpStatus.BAD_REQUEST, "Username already exist");
        }
        User user = new User();
        user.setUsername(registerRequest.username());
        user.setEmail(registerRequest.email());
        user.setPassword(passwordEncoder.encode(registerRequest.password()));

        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByName("ROLE_USER");
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);
        return "User Registered successfully";
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByUsernameOrEmail(loginRequest.usernameOrEmail(),loginRequest.usernameOrEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User does not exist by Username or email"));
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.usernameOrEmail(),
                loginRequest.password()
        ));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        UserDetails userDetails = (UserDetails) authenticate.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(a -> a.getAuthority().replace("ROLE_", "")).toList();
        String token = jwtTokenProvider.generateToken(authenticate);
        return new AuthResponse(token,user.getId(),roles);
    }

    @Override
    public List<UserData> userInfo() {
        return userRepository.findAll()
                .stream().map(user->new UserData(user.getUsername(),user.getId())).toList();
    }
}
