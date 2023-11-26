package com.epam.training.ticketservice.core;

import com.epam.training.ticketservice.core.user.*;
import org.junit.jupiter.api.Test;

import javax.swing.text.html.Option;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class AdminUserServiceImplementationTest {

        private final UserRepository userRepo = mock(UserRepository.class);

        private final UserService inTest = new UserServiceImplementation(userRepo);

        @Test
        void testLoginShouldSetLoggedInUserWhenUsernameAndPasswordAreCorrect(){
                //Given
                User user = new User("admin", "admin", Role.ADMIN);
                Optional<User> expected = Optional.of(user);
                when(userRepo.findByUsernameAndPassword("admin", "admin")).thenReturn(Optional.of(user));

                //When
                Optional<UserDto> actual = inTest.login("admin", "admin");

                //Then
                assertEquals(expected.get().getUsername(), actual.get().getUsername());
                assertEquals(expected.get().getRole(), actual.get().getRole());
                verify(userRepo).findByUsernameAndPassword("admin", "admin");
        }


        @Test
        void testLoginShouldReturnOptionalEmptyWhenLogInCredentialsAreIncorrect(){
                //Given
                Optional<User> expUser = Optional.empty();
                when(userRepo.findByUsernameAndPassword("kacsa", "kacsa")).thenReturn(Optional.empty());

                //When
                Optional<UserDto> actual = inTest.login("kacsa", "kacsa");

                //Then
                assertEquals(expUser, actual);
                verify(userRepo).findByUsernameAndPassword("kacsa","kacsa");
        }

        @Test
        void testLogoutShouldReturnOptionalEmptyWhenThereIsNoOneLoggedIn() {
                //Given
                Optional<User> user = Optional.empty();

                //When
                Optional<UserDto> actUser = inTest.logout();
                //Then
                assertEquals(user, actUser);
        }

        @Test
        void testLogoutShouldReturnLastLoggedinUserWhenUserIsLoggedIn() {
                //Given
                User user = new User("admin", "admin", Role.ADMIN);
                when(userRepo.findByUsernameAndPassword("admin", "admin")).thenReturn(Optional.of(user));
                Optional<UserDto> userDto = inTest.login("admin", "admin");

                //When
                Optional<UserDto> actLogout = inTest.logout();

                //Then
                assertEquals(actLogout, userDto);
        }
}
