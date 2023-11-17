package com.epam.training.ticketservice.ui;

import com.epam.training.ticketservice.core.user.UserDto;
import com.epam.training.ticketservice.core.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Optional;

@ShellComponent
@RequiredArgsConstructor
public class UserCommand {

    private final UserService userService;

    @ShellMethod(key = "sign in privileged", value = "administrators can log in")
    public String login(String username, String password){
        return userService.login(username, password)
                .map(userDto -> userDto + " is successfully logged in!")
                .orElse("Login failed due to incorrect credentials");
    }

    @ShellMethod(key = "sign out", value = "user sign out")
    public String signOut(){
        return userService.logout()
                .map(userDto -> userDto + " is logged out")
                .orElse("You need to log in first!");
    }

    @ShellMethod(key = "describe account", value = " details about the logged in user")
    public String description(){
        return userService.describe()
                .map(userDto -> "Signed in with privileged account '"+ userDto.getUsername()+"'")
                .orElse("You are not signed in");
    }



}
