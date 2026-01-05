package com.spss.glowlytic.service.impl;


import com.spss.glowlytic.dto.request.LoginRequest;
import com.spss.glowlytic.dto.response.LoginResponse;
import com.spss.glowlytic.entity.User;
import com.spss.glowlytic.repository.UserRepository;
import com.spss.glowlytic.service.AuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUsernameOrEmail(request.getIdentifier(), request.getIdentifier())
                .orElseThrow(() -> new RuntimeException("User not found"));
        boolean matches = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!matches) {
            throw new RuntimeException("Invalid password");
        }
        String accessToken = jwtService.generateToken(user.getUsername(), false);
        String refreshToken = jwtService.generateToken(user.getUsername(), true);
        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}