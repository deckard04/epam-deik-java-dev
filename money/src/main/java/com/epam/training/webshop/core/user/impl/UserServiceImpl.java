package com.epam.training.webshop.core.user.impl;


import com.epam.training.webshop.core.user.UserService;
import com.epam.training.webshop.core.user.model.UserDTO;
import com.epam.training.webshop.core.user.persistence.entity.User;
import com.epam.training.webshop.core.user.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private UserDTO loggedInUser = null;

    @Override
    public Optional<UserDTO> login(final String username, final String password) {
        final Optional<User> user = userRepository.findByUsernameAndPassword(username, password);
        if (user.isEmpty()) {
            return Optional.empty();
        }
        loggedInUser = new UserDTO(user.get().getUsername(), user.get().getRole());
        return describe();
    }

    @Override
    public Optional<UserDTO> logout() {
        final Optional<UserDTO> previouslyLoggedInUser = describe();
        loggedInUser = null;
        return previouslyLoggedInUser;
    }

    @Override
    public Optional<UserDTO> describe() {
        return Optional.ofNullable(loggedInUser);
    }

    @Override
    public User registerUser(final String username, final String password) {
        final User user = new User(username, password, User.Role.USER);
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUser(final Long id) {
        return userRepository.findById(id);
    }
}
