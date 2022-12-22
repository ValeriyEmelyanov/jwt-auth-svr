package com.example.jwtauthsvr.util;

import com.example.jwtauthsvr.model.Role;
import com.example.jwtauthsvr.model.User;
import com.example.jwtauthsvr.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class AdminCreator implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        String login = "admin";

        Optional<User> optional = userRepository.findByLogin(login);
        if (optional.isPresent()) {
            return;
        }

        User admin = new User();
        admin.setLogin(login);
        admin.setPassword(passwordEncoder.encode("12345"));
        admin.setEnabled(true);
        admin.setRoles(Set.of(Role.ADMIN));
        userRepository.save(admin);
    }
}
