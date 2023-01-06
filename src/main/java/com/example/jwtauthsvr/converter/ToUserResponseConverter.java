package com.example.jwtauthsvr.converter;

import com.example.jwtauthsvr.dto.UserResponse;
import com.example.jwtauthsvr.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ToUserResponseConverter implements Converter<User, UserResponse> {
    @Override
    public UserResponse convert(User u) {
        return UserResponse.builder()
                .id(u.getId())
                .login(u.getLogin())
                .enabled(u.getEnabled())
                .firstName(u.getFirstName())
                .lastName(u.getLastName())
                .phone(u.getPhone())
                .email(u.getEmail())
                .roles(u.getRoles())
                .build();
    }
}
