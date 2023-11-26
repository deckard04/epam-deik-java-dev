package com.epam.training.ticketservice.core;

import com.epam.training.ticketservice.core.movie.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class movieServiceImplementationTest {

        private static final Movie movie = new Movie("Sátántangó", "drama", 450);
        private static final MovieDto movieDtoEdited = new MovieDto("Sátántangó", "animated", 250);
        private static final MovieDto movieDto = new MovieDto("Sátántangó", "drama", 450);

        private static final MovieRepository movieRepo = mock(MovieRepository.class);

        private static final MovieServiceImplementation movieService = new MovieServiceImplementation(movieRepo);

        @Test
        void testGetMoviesListShouldReturnSatantangoWhenInputMovieNameIsSatantango() {
                //Given
                when(movieRepo.findAll()).thenReturn(List.of(movie));

                //when
                List<MovieDto> actual = movieService.getMovieList();

                //Then
                verify(movieRepo).findAll();
                assertEquals(movieRepo.findAll().size(), actual.size());
        }

        @Test
        void testCreateMovieShouldStoreTheGivenMovieWhenTheInputIsValid() {
                //Given
                when(movieRepo.save(movie)).thenReturn(movie);

                //When
                movieService.createMovie(movieDto);

                //Then
                verify(movieRepo).save(movie);
        }

        @Test
        void testUpdateMovieDetailsWhenMovieExists() {
                //Given
                when(movieRepo.findByName("Sátántangó")).thenReturn(Optional.of(movie));
                Optional<MovieDto> expected = Optional.of(movieDtoEdited);

                //When
                Optional<MovieDto> edited = movieService.updateMovie("Sátántangó", "animated", 250);

                //Then
                assertEquals(expected, edited);
        }

        @Test
        void testUpdateMovieDetailsShouldReturnOptionalEmptyWhenMovieDoesNotExist() {
                //Given
                when(movieRepo.findByName("Dummy")).thenReturn(Optional.empty());
                Optional<MovieDto> expected = Optional.empty();
                //When
                Optional<MovieDto> actual = movieService.updateMovie("Dummy", "dummy", 2000);

                //Then
                assertEquals(expected, actual);
        }
        @Test
        void testFindMovieByNameShouldReturnMovieWhenMovieExists() {
                //Given
                when(movieRepo.findByName("Sátántangó")).thenReturn(Optional.of(movie));
                Optional<Movie> expected = Optional.of(movie);
                //When
                Optional<Movie> actual = movieService.findByName("Sátántangó");
                //Then
                assertEquals(expected, actual);
        }

        @Test
        void testDeleteMovieShouldDeleteGivenMovie(){
                //Given
                when(movieRepo.findByName("Sátántangó")).thenReturn(Optional.of(movie));

                //When
                movieService.deleteMovie(movieDto.getName());

                //Then
                verify(movieRepo).deleteByName(movieDto.getName());
        }

}
