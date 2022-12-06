package com.example.jwtauthsvr.service.impl;

import com.example.jwtauthsvr.model.Role;
import com.example.jwtauthsvr.model.User;
import com.example.jwtauthsvr.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final List<User> users;

    public UserServiceImpl() {
        this.users = List.of(
                new User("admin", "54321", "Антон", "Иванов", Collections.singleton(Role.ADMIN)),
                new User("admin", "54321", "Сергей", "Петров", Collections.singleton(Role.ADMIN))
        );
    }

    @Override
    public Optional<User> getByLogin(@NonNull String login) {
        return users.stream()
                .filter(user -> login.equals(user.getLogin()))
                .findFirst();
    }
}