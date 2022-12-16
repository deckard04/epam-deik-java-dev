package com.epam.training.webshop.core.user;

import com.epam.training.webshop.core.user.model.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<UserDTO> login(String username, String password);

    Optional<UserDTO> logout();

    Optional<UserDTO> describe();

    UserDTO registerUser(String username, String password);

    UserDTO getUser(Long id);

    List<UserDTO> getUsers();

    void deleteUser(Long id);

    UserDTO updateUser(Long id, UserDTO user);
}
