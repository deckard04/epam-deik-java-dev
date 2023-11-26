package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.core.room.RoomDto;
import com.epam.training.ticketservice.core.room.RoomService;
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
public class RoomCommandTest {

        private static final RoomDto roomDto = RoomDto.builder()
                .name("Pedersoli")
                .rowNumb(10)
                .columnNumb(10)
                .build();

        @Autowired
        private Shell shell;

        @SpyBean
        private RoomService roomService;

        @Test
        void testRoomCreateCommandShouldSaveTheRoomsWhenAdminIsLoggedIn() {
                // Given
                shell.evaluate(() -> "sign in privileged admin admin");

                // When
                shell.evaluate(() -> "create room Pedersoli 10 10");

                // Then
                verify(roomService).createRoom(roomDto);
                assertTrue(roomService.listRooms().contains(roomDto));
        }

        @Test
        void testRoomCreateCommandShouldNotSaveRoomsWhenAdminIsNotLoggedIn(){
                //When
                shell.evaluate(() -> "create room Pedersoli 10 10");

                //then
                verify(roomService, times(0)).createRoom(roomDto);
        }
}
