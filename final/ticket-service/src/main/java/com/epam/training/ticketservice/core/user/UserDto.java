package com.epam.training.ticketservice.core.user;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserDto {

    private String username;
    private Role role;

    public UserDto(String username, Role role){
        this.username = username;
        this.role = role;
    }
}
