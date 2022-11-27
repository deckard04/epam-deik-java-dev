package com.epam.training.webshop.core.user.model;

import com.epam.training.webshop.core.user.persistence.entity.User;
import lombok.Value;

@Value
public class UserDTO {

    private final String username;
    private final User.Role role;
}
