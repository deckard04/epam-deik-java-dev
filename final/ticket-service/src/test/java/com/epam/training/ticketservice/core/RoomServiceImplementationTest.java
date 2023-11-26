package com.epam.training.ticketservice.core;

import com.epam.training.ticketservice.core.room.*;
import org.junit.jupiter.api.Test;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RoomServiceImplementationTest {

        private static final Room room = new Room("Pedersoli", 10, 10);

        private static final RoomDto roomDto = new RoomDto("Pedersoli", 10, 10);

        private static final RoomDto roomDtoEdited = new RoomDto("Pedersoli", 18, 16);

        private static final RoomRepository roomRepo = mock(RoomRepository.class);

        private static final RoomServiceImplementation serviceImplementation = new RoomServiceImplementation(roomRepo);

        @Test
        void testCreateRoomShouldStoreTheGivenRoomWhenRoomIsValid() {
                //Given
                when(roomRepo.save(room)).thenReturn(room);

                //When
                serviceImplementation.createRoom(roomDto);

                //Then
                verify(roomRepo).save(room);
        }

        @Test
        void testGetMovieListShouldReturnPedersoliWhenInputNameIsPedersoli() {
                //Given
                when(roomRepo.findAll()).thenReturn(List.of(room));

                //When
                List<RoomDto> actual = serviceImplementation.listRooms();

                //Then
                verify(roomRepo).findAll();
                assertEquals(actual.size(), roomRepo.findAll().size());
        }

        @Test
        void testUpdateMovieShouldUpdateMovieWhenMovieExists() {
                //Given
                when(roomRepo.findByName(room.getName())).thenReturn(Optional.of(room));
                Optional<RoomDto> expected = Optional.of(roomDtoEdited);

                //When
                Optional<RoomDto> actual = serviceImplementation.updateRoom("Pedersoli", 18, 16);

                //Then
                assertEquals(expected, actual);
        }

        @Test
        void testUpdateMovieShouldReturnOptionalEmptyWhenMovieDoesNotExist() {
                //Given
                when(roomRepo.findByName(room.getName())).thenReturn(Optional.of(room));
                Optional<RoomDto> expected = Optional.empty();
                //When
                Optional<RoomDto> actual = serviceImplementation.updateRoom("Dummy", 16, 16);

                //Then
                assertEquals(expected, actual);
        }


}
