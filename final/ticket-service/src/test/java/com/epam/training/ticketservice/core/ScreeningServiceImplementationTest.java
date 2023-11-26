package com.epam.training.ticketservice.core;


import com.epam.training.ticketservice.core.movie.Movie;
import com.epam.training.ticketservice.core.room.Room;
import com.epam.training.ticketservice.core.room.RoomDto;
import com.epam.training.ticketservice.core.screening.Screening;
import com.epam.training.ticketservice.core.screening.ScreeningDto;
import com.epam.training.ticketservice.core.screening.ScreeningRepository;
import com.epam.training.ticketservice.core.screening.ScreeningServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ScreeningServiceImplementationTest {

        private static final Room room = new Room("Pedersoli", 10, 10);

        private static final RoomDto roomDto = new RoomDto("Pedersoli", 10, 10);
        Screening screening = new Screening(movie, room, LocalDateTime.of(2022, Month.APRIL, 21, 12, 0), LocalDateTime.of(2022, Month.APRIL, 21, 19, 30));
        private static final Movie movie = new Movie("Sátántangó", "drama", 450);
        private static final ScreeningDto screeningDto = new ScreeningDto(room, movie, LocalDateTime.of(2022, Month.APRIL, 21, 12, 0), LocalDateTime.of(2022, Month.APRIL, 21, 19, 30));
        private static final ScreeningDto screeningDtoOverlapping = new ScreeningDto(room, movie, LocalDateTime.of(2022, Month.APRIL, 21, 13, 0), LocalDateTime.of(2022, Month.APRIL, 21, 17, 30));
        private static final ScreeningDto screeningDtoInBreakPeriod = new ScreeningDto(room, movie, LocalDateTime.of(2022, Month.APRIL, 21, 19, 23), LocalDateTime.of(2022, Month.APRIL, 22, 2, 30));

        private static final ScreeningRepository screeningRepo = mock(ScreeningRepository.class);
        private static final ScreeningServiceImplementation screeningService = new ScreeningServiceImplementation(screeningRepo);

        @Test
        void testCreateScreeningShouldCreateScreeningWhenThereIsNoOverlapping() {
                //Given
                when(screeningRepo.findByScreeningDateGreaterThanEqualAndScreeningEndDateLessThanEqualAndRoomName(
                        screeningDto.getScreeningDate(),
                        screeningDto.getScreeningEndDate(),
                        screeningDto.getRoom().getName())).thenReturn(Optional.empty());
                when(screeningRepo.findByScreeningDateGreaterThanEqualAndScreeningDateLessThanEqualAndRoomName(
                        screeningDto.getScreeningDate(),
                        screeningDto.getScreeningEndDate(),
                        screeningDto.getRoom().getName())).thenReturn(Optional.empty());
                when(screeningRepo.findByScreeningEndDateGreaterThanEqualAndScreeningEndDateLessThanEqualAndRoomName(
                        screeningDto.getScreeningDate(),
                        screeningDto.getScreeningEndDate(),
                        screeningDto.getRoom().getName())).thenReturn(Optional.empty());
                when(screeningRepo.findByScreeningDateLessThanEqualAndScreeningEndDateGreaterThanEqualAndRoomName(
                        screeningDto.getScreeningDate(),
                        screeningDto.getScreeningEndDate(),
                        screeningDto.getRoom().getName())).thenReturn(Optional.empty());

                //When
                screeningService.createScreening(screeningDto);

                //Then
                verify(screeningRepo).findByScreeningDateGreaterThanEqualAndScreeningEndDateLessThanEqualAndRoomName(
                                screeningDto.getScreeningDate(),
                        screeningDto.getScreeningEndDate(),
                        screeningDto.getRoom().getName());
                verify(screeningRepo).findByScreeningDateGreaterThanEqualAndScreeningDateLessThanEqualAndRoomName(
                        screeningDto.getScreeningDate(),
                        screeningDto.getScreeningEndDate(),
                        screeningDto.getRoom().getName());

                verify(screeningRepo).findByScreeningEndDateGreaterThanEqualAndScreeningEndDateLessThanEqualAndRoomName(
                        screeningDto.getScreeningDate(),
                        screeningDto.getScreeningEndDate(),
                        screeningDto.getRoom().getName());

                verify(screeningRepo).findByScreeningDateLessThanEqualAndScreeningEndDateGreaterThanEqualAndRoomName(
                        screeningDto.getScreeningDate(),
                        screeningDto.getScreeningEndDate(),
                        screeningDto.getRoom().getName());

        }

        @Test
        void testCreateScreeningShouldNotCreateScreeningWhenThereIsAnOverlapping() {
                //Given

                when(screeningRepo.findByScreeningDateGreaterThanEqualAndScreeningEndDateLessThanEqualAndRoomName(
                        screeningDtoOverlapping.getScreeningDate(),
                        screeningDtoOverlapping.getScreeningEndDate(),
                        screeningDtoOverlapping.getRoom().getName())).thenReturn(Optional.of(screening));

                //When
                screeningService.createScreening(screeningDtoOverlapping);
                //Then
                verify(screeningRepo).findByScreeningDateGreaterThanEqualAndScreeningEndDateLessThanEqualAndRoomName(
                        screeningDtoOverlapping.getScreeningDate(),
                        screeningDtoOverlapping.getScreeningEndDate(),
                        screeningDtoOverlapping.getRoom().getName());
        }

        @Test
        void testCreateScreeningShouldNotCreateScreeningWhenItWouldStartInTheBreakPeriod() {
                //Given
                when(screeningRepo
                        .findByScreeningEndDateGreaterThanEqualAndScreeningEndDateLessThanEqualAndRoomName(
                                screeningDtoInBreakPeriod
                                        .getScreeningDate()
                                        .minusSeconds(10 * 60),
                                screeningDtoInBreakPeriod.getScreeningDate(),
                                screeningDtoInBreakPeriod.getRoom().getName())).thenReturn(Optional.of(screening));

                //When
                screeningService.createScreening(screeningDtoInBreakPeriod);
                //Then
                verify(screeningRepo).findByScreeningEndDateGreaterThanEqualAndScreeningEndDateLessThanEqualAndRoomName(
                        screeningDtoInBreakPeriod
                                .getScreeningDate()
                                .minusSeconds(10 * 60),
                        screeningDtoInBreakPeriod.getScreeningDate(),
                        screeningDtoInBreakPeriod.getRoom().getName());

        }

        @Test
        void GetScreeningListShouldReturnScreeningListWhenScreeningExists() {
                //Given

                when(screeningRepo.findAll()).thenReturn(List.of(screening));
                //When
                List<ScreeningDto> actual = screeningService.getScreeningList();

                //Then
                verify(screeningRepo).findAll();
                assertEquals(screeningRepo.findAll().size(), actual.size());
        }
}
