package com.epam.training.ticketservice.core.movie;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findByName(String name);

    @Transactional
    void deleteByName(String name);
}
