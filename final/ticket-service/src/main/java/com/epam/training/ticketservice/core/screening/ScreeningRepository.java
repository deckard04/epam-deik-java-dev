package com.epam.training.ticketservice.core.screening;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Long> {
    Optional<Screening> findByScreeningDateGreaterThanEqualAndScreeningEndDateLessThanEqualAndRoom_Name(LocalDateTime screeningDate, LocalDateTime screeningEndDate, String name);

    Optional<Screening> findByScreeningDateGreaterThanEqualAndScreeningDateLessThanEqualAndRoom_Name(LocalDateTime screeningDate, LocalDateTime screeningDate1, String name);
    Optional<Screening> findByScreeningEndDateGreaterThanEqualAndScreeningEndDateLessThanEqualAndRoom_Name(LocalDateTime screeningDate, LocalDateTime screeningDate1, String name);

    Optional<Screening> findByScreeningDateLessThanEqualAndScreeningEndDateGreaterThanEqualAndRoom_Name(LocalDateTime screeningDate, LocalDateTime screeningEndDate, String name);

    Optional<Screening> findByScreeningEndDateGreaterThanEqual(LocalDateTime screeningDate);

    @Transactional
    void deleteByMovie_NameAndRoom_NameAndScreeningDate(String name, String name1, LocalDateTime screeningDate);


}
