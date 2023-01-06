package com.example.jwtauthsvr.service.impl;

import com.example.jwtauthsvr.dto.UserEnabledRequest;
import com.example.jwtauthsvr.dto.UserPageResponse;
import com.example.jwtauthsvr.dto.UserPasswordRequest;
import com.example.jwtauthsvr.dto.UserRequest;
import com.example.jwtauthsvr.dto.UserResponse;
import com.example.jwtauthsvr.exception.NotFoundException;
import com.example.jwtauthsvr.model.Role;
import com.example.jwtauthsvr.model.User;
import com.example.jwtauthsvr.repository.UserRepository;
import com.example.jwtauthsvr.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ConversionService conversionService;

    private final PasswordEncoder passwordEncoder;

    @Override
    @NonNull
    public Optional<User> getByLogin(@NonNull String login) {
        Assert.notNull(login, "login should not be null");
        log.info("Requested the User with login: {}", login);

        return userRepository.findByLogin(login);
    }

    @Override
    @NonNull
    public UserPageResponse getUsers(@NonNull Integer page, @NonNull Integer size) {
        Assert.notNull(page, "page should not be null");
        Assert.notNull(size, "size should not be null");
        log.info("Requested the page {} size {}", page, size);

        Page<User> userPage = userRepository.findAll(PageRequest.of(page, size));
        return conversionService.convert(userPage, UserPageResponse.class);
    }

    @Override
    @NonNull
    public UserResponse create(@NonNull UserRequest userRequest) {
        Assert.notNull(userRequest, "userRequest should not be null");
        log.info("Requested saving the user with login {}", userRequest.getLogin());

        User user = conversionService.convert(userRequest, User.class);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setEnabled(true);
        User saved = userRepository.save(user);
        return conversionService.convert(saved, UserResponse.class);
    }

    @Override
    @NonNull
    public UserResponse getById(@NonNull Long id) {
        Assert.notNull(id, "id should not be null");
        log.info("Requested the user with id {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));
        return conversionService.convert(user, UserResponse.class);
    }

    @Override
    @NonNull
    public UserResponse update(@NonNull Long id, @NonNull UserRequest userRequest) {
        Assert.notNull(id, "id should not be null");
        Assert.notNull(userRequest, "userRequest should not be null");
        log.info("Requested updating the user with id {}", id);

        User fetched = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));
        User user = conversionService.convert(userRequest, User.class);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setEnabled(fetched.getEnabled());
        user.setId(id);
        User saved = userRepository.save(user);
        return conversionService.convert(saved, UserResponse.class);
    }

    @Override
    @NonNull
    public void delete(@NonNull Long id) {
        Assert.notNull(id, "id should not be null");
        log.info("Requested deleting the user with id {}", id);

        userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));
        userRepository.deleteById(id);
    }

    @Override
    @NonNull
    public UserResponse enable(@NonNull Long id, @NonNull UserEnabledRequest enabledRequest) {
        Assert.notNull(id, "id should not be null");
        Assert.notNull(enabledRequest, "enabledRequest should not be null");
        log.info("Requested to enable the user with id {}", id);

        User fetched = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));
        fetched.setEnabled(enabledRequest.getEnabled());
        User saved = userRepository.save(fetched);
        return conversionService.convert(saved, UserResponse.class);
    }

    @Override
    @NonNull
    public UserResponse changePassword(@NonNull Long id, @NonNull UserPasswordRequest passwordRequest) {
        Assert.notNull(id, "id should not be null");
        Assert.notNull(passwordRequest, "passwordRequest should not be null");
        log.info("Requested to change password of the user with id {}", id);

        User fetched = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));
        fetched.setPassword(passwordEncoder.encode(passwordRequest.getPassword()));
        User saved = userRepository.save(fetched);
        return conversionService.convert(saved, UserResponse.class);
    }
}
