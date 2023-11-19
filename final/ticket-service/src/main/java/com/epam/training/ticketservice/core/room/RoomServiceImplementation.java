package com.epam.training.ticketservice.core.room;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomServiceImplementation implements RoomService{

    private final RoomRepository roomRepository;

    public void createRoom(RoomDto roomDto){
        Room room = new Room();
        room.setName(roomDto.getName());
        room.setRowNumb(roomDto.getRowNumb());
        room.setColumnNumb(roomDto.getColumnNumb());
        roomRepository.save(room);
    }

    @Override
    public Optional<Room> findByName(String name) {
        return roomRepository.findByName(name);
    }

    @Override
    public Optional<RoomDto> updateRoom(String name, int rowNum, int columnNum) {
        Optional<Room> room = roomRepository.findByName(name);
        if (room.isEmpty()){
            return Optional.empty();
        }
        room.get().setRowNumb(rowNum);
        room.get().setColumnNumb(columnNum);
        roomRepository.save(room.get());
        return Optional.of(new RoomDto(room.get().getName(), room.get().getRowNumb(), room.get().getColumnNumb()));
    }

    @Override
    public void deleteRoomByName(String name) {
        roomRepository.deleteByName(name);
    }

    @Override
    public List<RoomDto> listRooms() {
       return  roomRepository.findAll().stream()
                .map(this::roomsToDto)
                .toList();
    }

    private RoomDto roomsToDto(Room room) {
        return RoomDto.builder()
                .name(room.getName())
                .rowNumb(room.getRowNumb())
                .columnNumb(room.getColumnNumb())
                .build();
    }




}
