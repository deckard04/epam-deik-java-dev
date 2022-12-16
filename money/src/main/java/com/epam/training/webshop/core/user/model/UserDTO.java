package com.epam.training.webshop.core.user.model;

import com.epam.training.webshop.core.user.persistence.entity.User;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDTO {

    private final Long id;
    private final String username;
    private final String password;
    private final User.Role role;
}
