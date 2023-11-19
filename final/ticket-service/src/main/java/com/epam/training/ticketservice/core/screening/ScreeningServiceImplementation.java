package com.epam.training.ticketservice.core.screening;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScreeningServiceImplementation implements ScreeningService {

    private final ScreeningRepository screeningRepository;
    @Override
    public String createScreening(ScreeningDto screeningDto) {
        Screening screening = new Screening();
        if (screenOverlapping(screeningDto)) {
            return "There is an overlapping screening";
        }
        if (breakPeriod(screeningDto)){
            return "This would start in the break period after another screening in this room";
        }
        screening.setRoom(screeningDto.getRoom());
        screening.setMovie(screeningDto.getMovie());
        screening.setScreeningDate(screeningDto.getScreeningDate());
        screening.setScreeningEndDate(screeningDto.getScreeningEndDate());
        screeningRepository.save(screening);
        return screening.toString();
    }

    private boolean breakPeriod(ScreeningDto screeningDto) {
        return screeningRepository
                .findByScreeningEndDateGreaterThanEqualAndScreeningEndDateLessThanEqualAndRoom_Name
                        (screeningDto
                                        .getScreeningDate()
                                        .minusSeconds(10*60),
                                screeningDto.getScreeningDate(),
                                screeningDto.getRoom().getName())
                .isPresent();
    }

    @Override
    public boolean screenOverlapping(ScreeningDto screeningDto){
        return screeningRepository.findByScreeningDateGreaterThanEqualAndScreeningEndDateLessThanEqualAndRoom_Name(screeningDto.getScreeningDate(), screeningDto.getScreeningEndDate(), screeningDto.getRoom().getName()).isPresent()
                || screeningRepository.findByScreeningDateGreaterThanEqualAndScreeningDateLessThanEqualAndRoom_Name(screeningDto.getScreeningDate(), screeningDto.getScreeningEndDate(), screeningDto.getRoom().getName()).isPresent()
                || screeningRepository.findByScreeningEndDateGreaterThanEqualAndScreeningEndDateLessThanEqualAndRoom_Name(screeningDto.getScreeningDate(), screeningDto.getScreeningEndDate(), screeningDto.getRoom().getName()).isPresent()
                || screeningRepository.findByScreeningDateLessThanEqualAndScreeningEndDateGreaterThanEqualAndRoom_Name(screeningDto.getScreeningDate(), screeningDto.getScreeningEndDate(), screeningDto.getRoom().getName()).isPresent();
    }

    @Override
    public List<ScreeningDto> getScreeningList() {
        return screeningRepository.findAll()
                .stream()
                .map(this::entityToDto)
                .toList();
    }

    @Override
    public void deleteScreening(String movieName, String roomName, LocalDateTime screeningDate) {
        screeningRepository.deleteByMovie_NameAndRoom_NameAndScreeningDate(movieName, roomName, screeningDate);
    }

    public ScreeningDto entityToDto(Screening screening){
        return ScreeningDto.builder()
                .movie(screening.getMovie())
                .room(screening.getRoom())
                .screeningDate(screening.getScreeningDate())
                .screeningEndDate(screening.getScreeningEndDate())
                .build();
    }


}
