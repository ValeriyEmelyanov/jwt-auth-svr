package com.example.jwtauthsvr.converter;

import com.example.jwtauthsvr.dto.UserPageResponse;
import com.example.jwtauthsvr.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ToUserPageResponseConverter implements Converter<Page<User>, UserPageResponse> {
    private final ToUserResponseConverter toUserResponseConverter;

    @Override
    public UserPageResponse convert(Page<User> p) {
        return UserPageResponse.builder()
                .content(p.getContent().stream()
                        .map(toUserResponseConverter::convert)
                        .collect(Collectors.toList())
                )
                .page(p.getNumber())
                .size(p.getSize())
                .totalItems(p.getTotalElements())
                .totalPages(p.getTotalPages())
                .build();
    }
}
