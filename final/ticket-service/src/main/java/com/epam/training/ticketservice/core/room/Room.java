package com.epam.training.ticketservice.core.room;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Room {

    @Id
    private String name;
    private int rowNumb;
    private int columnNumb;

    @Override
    public String toString() {
        return "Room{" +
                ", name='" + name + '\'' +
                ", rowNumb=" + rowNumb +
                ", columnNumb=" + columnNumb +
                '}';
    }
}
