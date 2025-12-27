package com.cinemaabyss.events.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class MovieEvent extends Event {

    private String userId;
    private String movieId;
    private String title;
    private String action;

    public MovieEvent(EventType type) {
        super(type);
    }
}
