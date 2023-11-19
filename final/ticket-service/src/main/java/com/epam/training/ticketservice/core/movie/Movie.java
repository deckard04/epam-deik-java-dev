package com.epam.training.ticketservice.core.movie;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Movie {

    @Id
    private String name;
    private String category;
    private int lengthInMinute;

    @Override
    public String toString() {
        return "Movie{" +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", lengthInMinute=" + lengthInMinute +
                '}';
    }
}
