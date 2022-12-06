package com.example.jwtauthsvr.controller;

import com.example.jwtauthsvr.model.AuthInfo;
import com.example.jwtauthsvr.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class RoutineController {

    private final AuthService authService;

    @PreAuthorize("hasAnyAuthority('USER')")
    @GetMapping("hello/user")
    public ResponseEntity<String> helloUser() {
        final AuthInfo authInfo = authService.getAuthInfo();
        return ResponseEntity.ok(String.format("Hello user %s!", authInfo.getPrincipal()));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("hello/admin")
    public ResponseEntity<String> helloAdmin() {
        final AuthInfo authInfo = authService.getAuthInfo();
        return ResponseEntity.ok(String.format("Hello admin %s!", authInfo.getPrincipal()));
    }

}
