package com.example.jwtauthsvr.converter;

import com.example.jwtauthsvr.dto.UserRequest;
import com.example.jwtauthsvr.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ToUserConverter implements Converter<UserRequest, User> {
    @Override
    public User convert(UserRequest source) {
        User user = new User();
        user.setLogin(source.getLogin());
        user.setFirstName(source.getFirstName());
        user.setLastName(source.getLastName());
        user.setPhone(source.getPhone());
        user.setEmail(source.getEmail());
        user.setRoles(source.getRoles());
        return user;
    }
}
