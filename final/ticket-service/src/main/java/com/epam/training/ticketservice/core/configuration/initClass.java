package com.epam.training.ticketservice.core.configuration;

import com.epam.training.ticketservice.core.user.Role;
import com.epam.training.ticketservice.core.user.User;
import com.epam.training.ticketservice.core.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class initClass {

    private final UserRepository userRepository;

    @PostConstruct
    public void init(){
        User user = new User("admin", "admin", Role.ADMIN);
        userRepository.save(user);
    }
}
