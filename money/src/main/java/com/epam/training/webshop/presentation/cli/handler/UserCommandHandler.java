package com.epam.training.webshop.presentation.cli.handler;


import com.epam.training.webshop.core.user.UserService;
import com.epam.training.webshop.core.user.model.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Optional;

@ShellComponent
@AllArgsConstructor
public class UserCommandHandler {

    private final UserService userService;

    @ShellMethod(key = "user logout", value = "User logout")
    public String logout() {
        final Optional<UserDTO> user = userService.logout();
        if (user.isEmpty()) {
            return "You need to login first!";
        }
        return user.get() + " is logged out!";
    }

    @ShellMethod(key = "user login", value = "User login")
    public String login(final String username, final String password) {
        final Optional<UserDTO> user = userService.login(username, password);
        if (user.isEmpty()) {
            return "No such username or password!";
        }
        return user.get() + " is successfully logged in!";
    }

    @ShellMethod(key = "user print", value = "Get user information")
    public String print() {
        final Optional<UserDTO> user = userService.describe();
        if (user.isEmpty()) {
            return "You need to login first!";
        }
        return user.get().toString();
    }

    @ShellMethod(key = "user register", value = "User registration")
    public String registerUser(final String userName, final String password) {
        try {
            userService.registerUser(userName, password);
            return "Registration was successful!";
        } catch (Exception e) {
            return "Registration failed!";
        }
    }
}
