package com.epam.training.ticketservice.core.room;

import java.util.List;
import java.util.Optional;

public interface RoomService {

    void createRoom(RoomDto roomDto);

    Optional<Room> findByName(String name);

    Optional<RoomDto> updateRoom(String name, int rowNum, int columnNum);

    void deleteRoomByName(String name);

    List<RoomDto> listRooms();
}
