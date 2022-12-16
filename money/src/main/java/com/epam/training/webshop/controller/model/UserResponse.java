package com.epam.training.webshop.controller.model;

import com.epam.training.webshop.core.user.persistence.entity.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
public class UserResponse {

    private final Long id;
    private final String username;
    private final User.Role role;
}
