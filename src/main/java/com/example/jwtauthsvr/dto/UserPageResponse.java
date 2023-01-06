package com.example.jwtauthsvr.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class UserPageResponse {
    private final List<UserResponse> content;
    private final Integer page;
    private final Integer size;
    private final Long totalItems;
    private final Integer totalPages;
}
