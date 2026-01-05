package com.spss.glowlytic.service.impl;

import com.spss.glowlytic.dto.request.CreateUserRequest;
import com.spss.glowlytic.dto.request.LoginRequest;
import com.spss.glowlytic.dto.request.LogoutRequest;
import com.spss.glowlytic.dto.response.LoginResponse;
import com.spss.glowlytic.entity.Role;
import com.spss.glowlytic.entity.User;
import com.spss.glowlytic.enums.ErrorCode;
import com.spss.glowlytic.enums.RoleName;
import com.spss.glowlytic.enums.UserStatus;
import com.spss.glowlytic.exception.AppException;
import com.spss.glowlytic.mapper.UserMapper;
import com.spss.glowlytic.repository.RoleRepository;
import com.spss.glowlytic.repository.UserRepository;
import com.spss.glowlytic.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RedisService redisService;


    @Override
    @Transactional
    public void register(CreateUserRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }
        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setStatus(UserStatus.ACTIVE);
        user.setIsBanned(false);
        user.setIsDeleted(false);
        Role customerRole = roleRepository.findByName(RoleName.CUSTOMER)
                .orElseThrow(() -> new RuntimeException("Role CUSTOMER not initialized in DB"));
        user.setRole(customerRole);

        userRepository.save(user);
    }

    @Override
    public void logout(LogoutRequest request, String accessToken) {
        String finalAccessToken = accessToken.startsWith("Bearer ")
                ? accessToken.substring(7)
                : accessToken;
        long accessTokenRemainingTime = jwtService.getRemainingTime(finalAccessToken);
        if (accessTokenRemainingTime > 0) {
            redisService.save(finalAccessToken, "blacklisted", accessTokenRemainingTime, TimeUnit.MILLISECONDS);
        }
        if (request.getRefreshToken() != null && !request.getRefreshToken().isEmpty()) {
            String refreshToken = request.getRefreshToken();
            long refreshTokenRemainingTime = jwtService.getRemainingTime(refreshToken);
            if (refreshTokenRemainingTime > 0) {
                redisService.save(refreshToken, "blacklisted", refreshTokenRemainingTime, TimeUnit.MILLISECONDS);
            }
        }
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByUsernameOrEmail(request.getIdentifier())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        if (Boolean.TRUE.equals(user.getIsBanned())) {
            throw new AppException(ErrorCode.USER_BANNED);
        }
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new AppException(ErrorCode.INVALID_CREDENTIALS);
        }

        String accessToken = jwtService.generateToken(user.getUsername(), false);
        String refreshToken = jwtService.generateToken(user.getUsername(), true);
        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}