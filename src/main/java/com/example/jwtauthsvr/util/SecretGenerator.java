package com.example.jwtauthsvr.util;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

public class SecretGenerator {

    public static void main(String[] args) {
        System.out.println(generateSecret());
        System.out.println(generateSecret());
    }

    public static String generateSecret() {
        return Encoders.BASE64.encode(Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded());
    }

}
