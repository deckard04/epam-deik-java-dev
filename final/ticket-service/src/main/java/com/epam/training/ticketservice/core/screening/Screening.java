package com.epam.training.ticketservice.core.screening;

import com.epam.training.ticketservice.core.movie.Movie;
import com.epam.training.ticketservice.core.room.Room;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Data
public class Screening {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)

    private Movie movie;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "room_name")
    private Room room;

    private LocalDateTime screeningDate;
    private LocalDateTime screeningEndDate;


    public Screening(Movie movie, Room room, LocalDateTime screeningDate, LocalDateTime screeningEndDate) {
        this.movie = movie;
        this.room = room;
        this.screeningDate = screeningDate;
        this.screeningEndDate = screeningEndDate;
    }

    @Override
    public String toString() {
        return "Screening{"
                +
                "id="
                +
                id
                +
                ", movieName='"
                +
                movie
                +
                '\''
                +
                ", roomName='"
                +
                room
                +
                '\''
                +
                ", screeningDate="
                +
                screeningDate
                +
                '}';
    }
}
