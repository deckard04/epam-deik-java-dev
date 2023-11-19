package com.epam.training.ticketservice.core.screening;

import com.epam.training.ticketservice.core.movie.Movie;
import com.epam.training.ticketservice.core.room.Room;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
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



    @Override
    public String toString() {
        return "Screening{" +
                "id=" + id +
                ", movieName='" + movie + '\'' +
                ", roomName='" + room + '\'' +
                ", screeningDate=" + screeningDate +
                '}';
    }
}
