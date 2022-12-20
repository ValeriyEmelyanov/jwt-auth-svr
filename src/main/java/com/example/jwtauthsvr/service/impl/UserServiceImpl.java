package com.example.jwtauthsvr.service.impl;

import com.example.jwtauthsvr.model.User;
import com.example.jwtauthsvr.repository.UserRepository;
import com.example.jwtauthsvr.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> getByLogin(@NonNull String login) {
        return userRepository.findByLogin(login);
    }
}
