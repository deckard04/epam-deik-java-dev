package com.epam.training.ticketservice.core.room;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Room {

    @Id
    private String name;
    private int rowNumb;
    private int columnNumb;

    @Override
    public String toString() {
        return "Room{"
                +
                ", name='"
                +
                name
                +
                '\''
                +
                ", rowNumb="
                +
                rowNumb
                +
                ", columnNumb="
                +
                columnNumb
                +
                '}';
    }
}
