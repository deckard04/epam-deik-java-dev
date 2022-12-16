package com.epam.training.webshop.core.user.impl;


import com.epam.training.webshop.core.user.UserService;
import com.epam.training.webshop.core.user.model.UserDTO;
import com.epam.training.webshop.core.user.model.UserNotFoundException;
import com.epam.training.webshop.core.user.persistence.entity.User;
import com.epam.training.webshop.core.user.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        loggedInUser = UserDTO.builder()
                .username(user.get().getUsername())
                .role(user.get().getRole())
                .build();
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
    public UserDTO registerUser(final String username, final String password) {
        final User user = new User(username, password, User.Role.USER);
        final User savedUser = userRepository.save(user);
        return UserDTO.builder()
                .id(savedUser.getId())
                .username(savedUser.getUsername())
                .role(savedUser.getRole())
                .build();
    }

    @Override
    public UserDTO getUser(final Long id) {
        return userRepository.findById(id)
                .map(u -> UserDTO.builder().id(u.getId()).username(u.getUsername()).role(u.getRole()).build())
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public List<UserDTO> getUsers() {
        return ((List<User>) userRepository.findAll()).stream()
                .map(u -> UserDTO.builder().id(u.getId()).username(u.getUsername()).role(u.getRole()).build())
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(final Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO updateUser(final Long id, final UserDTO user) {
        final User userToUpdate = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setPassword(user.getPassword());
        userRepository.save(userToUpdate);
        return UserDTO.builder().id(userToUpdate.getId()).username(userToUpdate.getUsername()).role(userToUpdate.getRole()).build();
    }
}
