package com.example.jwtauthsvr.service;

import com.example.jwtauthsvr.dto.UserEnabledRequest;
import com.example.jwtauthsvr.dto.UserPageResponse;
import com.example.jwtauthsvr.dto.UserPasswordRequest;
import com.example.jwtauthsvr.dto.UserRequest;
import com.example.jwtauthsvr.dto.UserResponse;
import com.example.jwtauthsvr.model.User;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface UserService {

    Optional<User> getByLogin(@NonNull String login);

    UserPageResponse getUsers(Integer page, Integer size);

    UserResponse create(UserRequest userRequest);

    UserResponse getById(Long id);

    UserResponse update(Long id, UserRequest userRequest);

    void delete(Long id);

    UserResponse enable(Long id, UserEnabledRequest enabledRequest);

    UserResponse changePassword(Long id, UserPasswordRequest passwordRequest);
}
