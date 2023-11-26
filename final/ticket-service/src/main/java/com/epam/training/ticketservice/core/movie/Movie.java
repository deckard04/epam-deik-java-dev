package com.epam.training.ticketservice.core.movie;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Movie {

    @Id
    private String name;
    private String category;
    private int lengthInMinute;

    @Override
    public String toString() {
        return "Movie{"
                +
                ", name='"
                +
                name + '\''
                +
                ", category='"
                +
                category
                +
                '\''
                +
                ", lengthInMinute="
                +
                lengthInMinute
                +
                '}';
    }
}
