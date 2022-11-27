package com.epam.training.webshop.core.user;
import com.epam.training.webshop.core.user.model.UserDTO;
import com.epam.training.webshop.core.user.persistence.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<UserDTO> login(String username, String password);

    Optional<UserDTO> logout();

    Optional<UserDTO> describe();

    User registerUser(String username, String password);

    Optional<User> getUser(Long id);
}
