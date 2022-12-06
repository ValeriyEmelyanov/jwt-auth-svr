package com.example.jwtauthsvr.service;

import com.example.jwtauthsvr.model.User;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface UserService {

    Optional<User> getByLogin(@NonNull String login);

}
