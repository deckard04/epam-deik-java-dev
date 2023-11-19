package com.epam.training.ticketservice.core.room;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Value
@Builder
public class RoomDto {

    private String name;
    private int rowNumb;
    private int columnNumb;

    @Override
    public String toString() {
        return  "Room " + name + " with "+
                rowNumb*columnNumb + " seats, " +
                rowNumb + " rows and " + columnNumb +
                " columns";

    }
}
