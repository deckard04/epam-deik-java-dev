package com.epam.training.ticketservice.ui.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.epam.training.ticketservice.core.movie.Movie;
import com.epam.training.ticketservice.core.movie.MovieDto;
import com.epam.training.ticketservice.core.movie.MovieService;
import com.epam.training.ticketservice.core.room.Room;
import com.epam.training.ticketservice.core.room.RoomDto;
import com.epam.training.ticketservice.core.room.RoomService;
import com.epam.training.ticketservice.core.screening.ScreeningDto;
import com.epam.training.ticketservice.core.screening.ScreeningService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.shell.Shell;
import org.springframework.test.context.ActiveProfiles;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("CTest")
public class ScreenCommandTest {

        private static final Room room = new Room("Pedersoli", 10, 10);
        private static final Movie movie = new Movie("Sátántangó", "drama", 450);


        private final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        @Autowired
        private Shell shell;

        @SpyBean
        private ScreeningService screeningService;
        @SpyBean
        private MovieService movieService;
        @SpyBean
        private RoomService roomService;

        @Test
        void testScreenCreateCommandShouldSaveTheScreeningWhenAdminIsLoggedIn() {
                // Given
                shell.evaluate(() -> "sign in privileged admin admin");

                // When
                shell.evaluate(() -> "create room Pedersoli 10 10");
                shell.evaluate(()-> "create movie Sátántangó drama 450");
                shell.evaluate(() -> "create screening Sátántangó Pedersoli \"2022-04-21 12:00:00\"");
                LocalDateTime  date = LocalDateTime.parse("2022-04-21 12:00", format);
                LocalDateTime  dateEnd = LocalDateTime.parse("2022-04-21 19:30", format);
                ScreeningDto screeningDto = new ScreeningDto(room, movie, date, dateEnd);
                String result = screeningService.createScreening(screeningDto);
                // Then
                assertTrue(screeningService.getScreeningList().contains(screeningDto));
        }

        @Test
        void testGetScreeningListShouldReturnScreeningListWhenIsPresent() {

                //When
                shell.evaluate(() -> "list screenings");

        }

}
