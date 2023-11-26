package com.epam.training.ticketservice.core.screening;

import com.epam.training.ticketservice.core.movie.Movie;
import com.epam.training.ticketservice.core.room.Room;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
public class ScreeningDto {

    private Room room;
    private Movie movie;
    private LocalDateTime screeningDate;
    private LocalDateTime screeningEndDate;

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedScreeningDate = screeningDate.format(format);
        return movie.getName()
                +
                " ("
                +
                movie.getCategory()
                +
                ", "
                +
                movie.getLengthInMinute()
                +
                " minutes), screened in room "
                +
                room.getName()
                +
                ", at "
                +
                formattedScreeningDate + System.lineSeparator();

    }
}
