
package com.epam.training.ticketservice.core.movie;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MovieDto {
    private final String name;
    private final String category;
    private final int movieLength;

    @Override
    public String toString() {
        return  name + " (" + category + ", " + movieLength + " minutes)"+System.lineSeparator();
    }
}
