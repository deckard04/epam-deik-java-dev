package com.epam.training.ticketservice.ui;

import com.epam.training.ticketservice.core.movie.MovieRepository;
import com.epam.training.ticketservice.core.movie.MovieService;
import com.epam.training.ticketservice.core.room.RoomRepository;
import com.epam.training.ticketservice.core.room.RoomService;
import com.epam.training.ticketservice.core.screening.ScreeningDto;
import com.epam.training.ticketservice.core.screening.ScreeningService;
import com.epam.training.ticketservice.core.user.Role;
import com.epam.training.ticketservice.core.user.UserDto;
import com.epam.training.ticketservice.core.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@ShellComponent
@RequiredArgsConstructor
public class ScreenCommand {

    private final ScreeningService screeningService;
    private final UserService userService;
    private final RoomService roomService;
    private final MovieService movieService;
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private LocalDateTime date;

    @ShellMethodAvailability("isAvailable")
    @ShellMethod(key = "create screening", value = " creating a screening in a cinema room")
    public String createScreening(String movieName, String roomName, String screeningTime) throws ParseException {
        date = LocalDateTime.parse(screeningTime, format);
        ScreeningDto screeningDto = ScreeningDto.builder()
                .movie(movieService.findByName(movieName).get())
                .room(roomService.findByName(roomName).get())
                .screeningDate(date)
                .screeningEndDate(date.plusSeconds(movieService.findByName(movieName).get().getLengthInMinute()* 60L))
                .build();
        return screeningService.createScreening(screeningDto);
    }

    @ShellMethod(key = "list screenings", value = "Shows all the existing screening")
    public String listScreening(){
        if (screeningService.getScreeningList().isEmpty()){
            return "There are no screenings";
        }
        return screeningService.getScreeningList()
                .stream().map(Objects::toString)
                .collect(Collectors.joining(""));
    }

    @ShellMethod(key = "delete screening", value = "delete specific screening")
    public void deleteScreening(String movieName, String roomName, String screeningDate){
        date = LocalDateTime.parse(screeningDate, format);
        screeningService.deleteScreening(movieName, roomName, date);
    }



    private Availability isAvailable(){
        Optional<UserDto> user = userService.describe();
        return user.isPresent() && user.get().getRole() == Role.ADMIN
                ? Availability.available()
                : Availability.unavailable("You are not an admin!");
    }
}
