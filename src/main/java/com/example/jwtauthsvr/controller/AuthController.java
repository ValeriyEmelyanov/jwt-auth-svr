package com.example.jwtauthsvr.controller;

import com.example.jwtauthsvr.dto.AuthRequest;
import com.example.jwtauthsvr.dto.AuthResponse;
import com.example.jwtauthsvr.dto.RefreshJwtRequest;
import com.example.jwtauthsvr.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        final AuthResponse tokens = authService.login(request);
        return ResponseEntity.ok(tokens);
    }

    @PostMapping("token")
    public ResponseEntity<AuthResponse> getNewAccessToken(@RequestBody RefreshJwtRequest request) {
        final AuthResponse tokens = authService.getAccessToken(request);
        return ResponseEntity.ok(tokens);
    }

    @PostMapping("refresh")
    public ResponseEntity<AuthResponse> getNewRefreshToken(@RequestBody RefreshJwtRequest request) {
        final AuthResponse tokens = authService.refresh(request);
        return ResponseEntity.ok(tokens);
    }

}
