package com.example.jwtauthsvr.service;

import com.example.jwtauthsvr.dto.RefreshJwtRequest;
import com.example.jwtauthsvr.dto.AuthRequest;
import com.example.jwtauthsvr.dto.AuthResponse;
import com.example.jwtauthsvr.model.AuthInfo;
import org.springframework.lang.NonNull;

public interface AuthService {
    public AuthInfo getAuthInfo();

    public AuthResponse login(@NonNull AuthRequest request);

    public AuthResponse getAccessToken(@NonNull RefreshJwtRequest request);

    public AuthResponse refresh(@NonNull RefreshJwtRequest request);

}
