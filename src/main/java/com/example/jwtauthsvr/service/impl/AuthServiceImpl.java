package com.example.jwtauthsvr.service.impl;

import com.example.jwtauthsvr.dto.RefreshJwtRequest;
import com.example.jwtauthsvr.dto.AuthRequest;
import com.example.jwtauthsvr.dto.AuthResponse;
import com.example.jwtauthsvr.exception.AuthException;
import com.example.jwtauthsvr.model.AuthInfo;
import com.example.jwtauthsvr.model.User;
import com.example.jwtauthsvr.security.JwtProvider;
import com.example.jwtauthsvr.service.AuthService;
import com.example.jwtauthsvr.service.UserService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final Map<String, String> refreshStorage = new ConcurrentHashMap<>();
    private final JwtProvider jwtProvider;

    @Override
    public AuthInfo getAuthInfo() {
        return (AuthInfo) SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public AuthResponse login(@NonNull AuthRequest request) {
        final User user = userService.getByLogin(request.getLogin())
                .orElseThrow(() -> new AuthException("Пользователь не найден"));
        if (user.getPassword().equals(request.getPassword())) {
            final String accessToken = jwtProvider.generateAccessToken(user);
            final String refreshToken = jwtProvider.generateRefreshToken(user);
            refreshStorage.put(user.getLogin(), refreshToken);
            return new AuthResponse(accessToken, refreshToken);
        } else {
            throw new AuthException("Неправильный пароль");
        }
    }

    @Override
    public AuthResponse getAccessToken(@NonNull RefreshJwtRequest request) {
        String refreshToken = request.getRefreshToken();
        Assert.notNull(request, "Невалидный JWT токен");
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String savedRefreshToken = refreshStorage.get(login);
            if (savedRefreshToken != null && savedRefreshToken.equals(refreshToken)) {
                final User user = userService.getByLogin(login)
                        .orElseThrow(() -> new AuthException("Пользователь не найден"));
                final String accessToken = jwtProvider.generateAccessToken(user);
                return new AuthResponse(accessToken, null);
            }
        }
        throw new AuthException("Невалидный JWT токен");
    }

    @Override
    public AuthResponse refresh(@NonNull RefreshJwtRequest request) {
        String refreshToken = request.getRefreshToken();
        Assert.notNull(request, "Невалидный JWT токен");
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String savedRefreshToken = refreshStorage.get(login);
            if (savedRefreshToken != null && savedRefreshToken.equals(refreshToken)) {
                final User user = userService.getByLogin(login)
                        .orElseThrow(() -> new AuthException("Пользователь не найден"));
                final String accessToken = jwtProvider.generateAccessToken(user);
                final String newRefreshToken = jwtProvider.generateRefreshToken(user);
                refreshStorage.put(user.getLogin(), newRefreshToken);
                return new AuthResponse(accessToken, newRefreshToken);
            }
        }
        throw new AuthException("Невалидный JWT токен");
    }
}
