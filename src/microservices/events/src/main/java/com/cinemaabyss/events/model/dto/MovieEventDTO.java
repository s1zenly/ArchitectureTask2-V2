package com.cinemaabyss.events.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MovieEventDTO {

    @NotBlank
    @JsonProperty("movie_id")
    private String movieId;

    @NotBlank
    @JsonProperty
    private String title;

    @NotBlank
    @JsonProperty
    private String action;

    @NotBlank
    @JsonProperty("user_id")
    private String userId;
}
