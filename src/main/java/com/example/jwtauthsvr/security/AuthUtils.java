package com.example.jwtauthsvr.security;

import com.example.jwtauthsvr.model.AuthInfo;
import com.example.jwtauthsvr.model.Role;
import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthUtils {

    public static AuthInfo newAuthInfo(Claims claims) {
        final AuthInfo authInfo = new AuthInfo();
        authInfo.setRoles(getRoles(claims));
        authInfo.setFirstName(claims.get("firstName", String.class));
        authInfo.setUsername(claims.getSubject());
        return authInfo;
    }

    private static Set<Role> getRoles(Claims claims) {
        final List<String> roles = claims.get("roles", List.class);
        return roles.stream()
                .map((Role::valueOf))
                .collect(Collectors.toSet());
    }

}
