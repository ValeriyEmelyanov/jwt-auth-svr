package com.example.jwtauthsvr.repository;

public interface KeyValueTempStorage {

    void put(final String key, final String value);

    String get(final String key);

}
