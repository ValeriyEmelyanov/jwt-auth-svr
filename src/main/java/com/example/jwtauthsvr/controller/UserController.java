package com.example.jwtauthsvr.controller;

import com.example.jwtauthsvr.dto.UserEnabledRequest;
import com.example.jwtauthsvr.dto.UserPageResponse;
import com.example.jwtauthsvr.dto.UserPasswordRequest;
import com.example.jwtauthsvr.dto.UserRequest;
import com.example.jwtauthsvr.dto.UserResponse;
import com.example.jwtauthsvr.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<UserPageResponse> getUsers(
            @RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(name = "size", defaultValue = "20", required = false) Integer size
    ) {
        return ResponseEntity.ok(userService.getUsers(page, size));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.create(userRequest));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<UserResponse> getUserById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable(name = "id") Long id,
            @RequestBody UserRequest userRequest
    ) {
        return ResponseEntity.ok(userService.update(id, userRequest));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<String> deleteUser(@PathVariable(name = "id") Long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("{id}/enabled")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<UserResponse> enableUser(
            @PathVariable(name = "id") Long id,
            @RequestBody UserEnabledRequest enabledRequest
            ) {
        return ResponseEntity.ok(userService.enable(id, enabledRequest));
    }

    @PatchMapping("{id}/password")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<UserResponse> changeUserPassword(
            @PathVariable(name = "id") Long id,
            @RequestBody UserPasswordRequest passwordRequest
            ) {
        return ResponseEntity.ok(userService.changePassword(id, passwordRequest));
    }
}
