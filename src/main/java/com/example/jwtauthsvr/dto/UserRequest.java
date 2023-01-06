package com.example.jwtauthsvr.dto;

import com.example.jwtauthsvr.model.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class UserRequest {

    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private Set<Role> roles;

}
