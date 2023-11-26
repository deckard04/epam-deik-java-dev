package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.core.movie.MovieDto;
import com.epam.training.ticketservice.core.movie.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.shell.Shell;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ActiveProfiles("CTest")
public class MovieCommandTest {

        private static final MovieDto movieDto = MovieDto.builder()
                .name("Satantango")
                .category("drama")
                .lengthInMinute(450)
                .build();

        @Autowired
        private Shell shell;

        @SpyBean
        private MovieService movieService;

        @Test
        void testProductCreateCommandShouldSaveTheMovieWhenAdminIsLoggedIn() {
                // Given
                shell.evaluate(() -> "sign in privileged admin admin");

                // When
                shell.evaluate(() -> "create movie Satantango drama 450");

                // Then
                verify(movieService).createMovie(movieDto);
                assertTrue(movieService.getMovieList().contains(movieDto));
        }

        @Test
        void testMovieCreateCommandShouldNotSaveMovieWhenAdminIsNotLoggedIn(){
                //When
                shell.evaluate(() -> "create movie Satantango drama 450");

                //then
                verify(movieService, times(0)).createMovie(movieDto);
        }
}
