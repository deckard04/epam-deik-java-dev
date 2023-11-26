
package com.epam.training.ticketservice.core.movie;

import lombok.Builder;
import lombok.Value;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Builder
@Value
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Data
public class MovieDto {

    private final String name;
    private final String category;
    private final int lengthInMinute;


    @Override
    public String toString() {
        return  name
                +
                " ("
                +
                category
                +
                ", "
                +
                lengthInMinute
                +
                " minutes)"
                +
                System.lineSeparator();
    }
}
