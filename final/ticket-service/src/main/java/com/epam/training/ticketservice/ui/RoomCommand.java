package com.epam.training.ticketservice.ui;

import com.epam.training.ticketservice.core.room.RoomDto;
import com.epam.training.ticketservice.core.room.RoomService;
import com.epam.training.ticketservice.core.user.Role;
import com.epam.training.ticketservice.core.user.UserDto;
import com.epam.training.ticketservice.core.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import com.epam.training.ticketservice.ui.MovieCommand;

import java.util.Optional;
import java.util.stream.Collectors;

@ShellComponent
@RequiredArgsConstructor
public class RoomCommand {

    private final RoomService roomService;
    private final UserService userService;

    @ShellMethodAvailability("isAvailable")
    @ShellMethod(key = "create room", value = "create a new cinema room")
    public RoomDto createRoom(String name, int rowNum, int columnNum) {
        RoomDto roomDto = RoomDto.builder()
                .name(name)
                .rowNumb(rowNum)
                .columnNumb(columnNum)
                .build();
        roomService.createRoom(roomDto);
        return roomDto;
    }

    @ShellMethodAvailability("isAvailable")
    @ShellMethod(key = "update room", value = "update existing cinema room details")
    public String updateRoom(String name, int rowNum, int columnNum) {
        return roomService.updateRoom(name, rowNum, columnNum)
                .map(roomDto1 -> roomDto1 + "updated!")
                .orElse("This room does not exist!");
    }

    @ShellMethodAvailability("isAvailable")
    @ShellMethod(key = "delete room", value = " delete existing cinema room")
    public void deleteRoom(String name) {
        roomService.deleteRoomByName(name);
    }

    @ShellMethod(key = "list rooms", value = "list all the cinema rooms")
    public String listRoom() {
        if (roomService.listRooms().isEmpty()) {
            return "There are no rooms at the moment";
        }
        return roomService.listRooms().stream()
                .map(Object::toString)
                .collect(Collectors.joining(""));
    }

    private Availability isAvailable() {
        Optional<UserDto> user = userService.describe();
        return user.isPresent() && user.get().getRole() == Role.ADMIN
                ? Availability.available()
                : Availability.unavailable("You are not an admin!");
    }
}
