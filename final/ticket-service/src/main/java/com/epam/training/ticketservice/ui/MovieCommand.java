package com.epam.training.ticketservice.ui;

import com.epam.training.ticketservice.core.movie.MovieDto;
import com.epam.training.ticketservice.core.user.Role;
import com.epam.training.ticketservice.core.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import com.epam.training.ticketservice.core.movie.MovieService;
import com.epam.training.ticketservice.core.user.UserService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@ShellComponent
@RequiredArgsConstructor
public class MovieCommand {

    private final MovieService movieService;
    private final UserService userService;

    @ShellMethodAvailability("isAvailable")
    @ShellMethod(key="create movie", value = "create a new movie")
    public MovieDto createMovie(String name, String category, int length) {
        MovieDto newMovie = MovieDto.builder()
                .name(name)
                .category(category)
                .movieLength(length)
                .build();
        movieService.createMovie(newMovie);
        return newMovie;
    }

    @ShellMethodAvailability("isAvailable")
    @ShellMethod(key="update movie", value = "update movie details. The name identifies the movie")
    public String updateMovie(String name, String category, int length){
        return movieService.updateMovie(name, category, length)
                .map(movieDto -> movieDto + " edited successfully")
                .orElse("Movie with this name does not exist");
    }

    @ShellMethod(key = "list movies", value = "shows all the movies")
    public String listMovies(){
        if (movieService.getMovieList().isEmpty()){
            return "There are no movies at the moment";
        }
        return movieService.getMovieList()
                .stream().map(Objects::toString)
                .collect(Collectors.joining(""));
    }

    @ShellMethodAvailability("isAvailable")
    @ShellMethod(key = "delete movie", value = "deletes the movie with the given name")
    public String deleteMovie(String name){
        movieService.deleteMovie(name);
        return "deleted";
    }

    private Availability isAvailable(){
        Optional<UserDto> user = userService.describe();
        return user.isPresent() && user.get().getRole() == Role.ADMIN
                ? Availability.available()
                : Availability.unavailable("You are not an admin!");
    }
}
