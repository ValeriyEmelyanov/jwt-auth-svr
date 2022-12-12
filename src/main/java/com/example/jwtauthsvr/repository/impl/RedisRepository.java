package com.example.jwtauthsvr.repository.impl;

import com.example.jwtauthsvr.repository.KeyValueTempStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class RedisRepository implements KeyValueTempStorage {

    private final RedisOperations<String, String> operations;

    @Value("${redis.duration.value}")
    private int refreshTokenDuration;


    @Override
    public void put(final String key, final String value) {
        operations.opsForValue().set(key, value, refreshTokenDuration, TimeUnit.MINUTES);
    }

    @Override
    public String get(final String key) {
        return operations.opsForValue().get(key);
    }

}
