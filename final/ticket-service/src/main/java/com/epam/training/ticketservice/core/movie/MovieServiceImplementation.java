package com.epam.training.ticketservice.core.movie;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieServiceImplementation implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public List<MovieDto> getMovieList() {
        return movieRepository.findAll()
                .stream()
                .map(this::mapEntityToDto)
                .toList();
    }

    @Override
    public void createMovie(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setName(movieDto.getName());
        movie.setCategory(movieDto.getCategory());
        movie.setLengthInMinute(movieDto.getLengthInMinute());
        movieRepository.save(movie);
    }

    @Override
    public Optional<MovieDto> updateMovie(String name, String category, int length) {
        Optional<Movie> movie = movieRepository.findByName(name);
        if (movie.isEmpty()) {
            return Optional.empty();
        }
        movie.get().setLengthInMinute(length);
        movie.get().setCategory(category);
        movieRepository.save(movie.get());
        return Optional.of(
                new MovieDto(movie.get().getName(),
                        movie.get().getCategory(),
                        movie.get().getLengthInMinute()));

    }

    @Override
    public void deleteMovie(String name) {
        movieRepository.deleteByName(name);
    }

    @Override
    public Optional<Movie> findByName(String name) {
        return movieRepository.findByName(name);
    }

    private MovieDto mapEntityToDto(Movie movie) {
        return MovieDto.builder()
                .name(movie.getName())
                .category(movie.getCategory())
                .lengthInMinute(movie.getLengthInMinute())
                .build();
    }
}
