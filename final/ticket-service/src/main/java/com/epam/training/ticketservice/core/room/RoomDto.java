package com.epam.training.ticketservice.core.room;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Value
@Builder
@AllArgsConstructor
public class RoomDto {

    private String name;
    private int rowNumb;
    private int columnNumb;

    @Override
    public String toString() {
        return  "Room "
                +
                name
                +
                " with "
                +
                rowNumb * columnNumb
                +
                " seats, "
                +
                rowNumb
                +
                " rows and "
                +
                columnNumb
                +
                " columns";

    }
}
