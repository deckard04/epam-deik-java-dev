package com.epam.training.ticketservice.core.movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<MovieDto> getMovieList();

    void createMovie(MovieDto movieDto);


    Optional<MovieDto> updateMovie(String name, String category, int length);

    void deleteMovie(String name);

    Optional<Movie> findByName(String name);
}
