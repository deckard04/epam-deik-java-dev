package com.epam.training.webshop.controller;

import com.epam.training.webshop.controller.model.UserRequest;
import com.epam.training.webshop.controller.model.UserResponse;
import com.epam.training.webshop.core.user.UserService;
import com.epam.training.webshop.core.user.model.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserRestController {

    private final UserService userService;


    @GetMapping("/{id}")
    public UserResponse getUser(final @PathVariable("id") Long id) {
        log.info("Get a User with ID: {}", id);
        final UserDTO user = userService.getUser(id);
        return new UserResponse(user.getId(), user.getUsername(), user.getRole());
    }

    @GetMapping
    public List<UserResponse> getUsers() {
        log.info("Get all Users");
        return userService.getUsers().stream()
                .map(u -> new UserResponse(u.getId(), u.getUsername(), u.getRole()))
                .collect(Collectors.toList());
    }

    @PostMapping
    public UserResponse createUser(final @RequestBody UserRequest userRequest) {
        log.info("Create a new User with details: {}", userRequest);
        final UserDTO user = userService.registerUser(userRequest.getUsername(), userRequest.getPassword());
        return new UserResponse(user.getId(), userRequest.getUsername(), user.getRole());
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(final @PathVariable Long id, final @RequestBody UserRequest userRequest) {
        log.info("Update a User with Id:{} with User details: {}", id, userRequest);
        final UserDTO updatedUser = userService.updateUser(id, UserDTO.builder()
                .username(userRequest.getUsername())
                .password(userRequest.getPassword())
                .build());
        return new UserResponse(updatedUser.getId(), updatedUser.getUsername(), updatedUser.getRole());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(final @PathVariable Long id) {
        log.info("Delete a User with ID:{}", id);
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
