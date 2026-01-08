package com.cinemaabyss.events.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MovieEventDTO {

    @JsonProperty("movie_id")
    private String movieId;

    @JsonProperty
    private String title;

    @JsonProperty
    private String action;

    @JsonProperty("user_id")
    private String userId;
}
