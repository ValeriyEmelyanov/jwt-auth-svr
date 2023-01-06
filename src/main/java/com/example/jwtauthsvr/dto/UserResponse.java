package com.example.jwtauthsvr.dto;

import com.example.jwtauthsvr.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
@Builder
public class UserResponse {
    private final Long id;
    private final String login;
    private final Boolean enabled;
    private final String firstName;
    private final String lastName;
    private final String phone;
    private final String email;
    private final Set<Role> roles;
}
