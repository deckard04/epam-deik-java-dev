package com.epam.training.ticketservice.core.screening;

import java.time.LocalDateTime;
import java.util.List;

public interface ScreeningService {
    String createScreening(ScreeningDto screeningDto);

    boolean screenOverlapping(ScreeningDto screeningDto);

    List<ScreeningDto> getScreeningList();

    void deleteScreening(String movieName, String roomName, LocalDateTime screeningDate);
}
