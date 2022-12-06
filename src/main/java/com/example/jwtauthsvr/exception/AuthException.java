package com.example.jwtauthsvr.exception;

public class AuthException extends RuntimeException {
    public AuthException(String message) {
        super(message);
    }
}
