package com.epam.training.webshop.core.user.persistence.init;

import com.epam.training.webshop.core.user.persistence.entity.User;
import com.epam.training.webshop.core.user.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Profile("! prod")
@Slf4j
@RequiredArgsConstructor
public class UserInitializer {

    private final UserRepository userRepository;

    @PostConstruct
    public void initUsers() {
        log.info("Saves users...");
        userRepository.save(new User("admin","1234", User.Role.ADMIN));
        userRepository.save(new User("test","1234", User.Role.USER));
        userRepository.findAll().forEach(System.out::println);
        log.info("Users saved.");
    }
}
