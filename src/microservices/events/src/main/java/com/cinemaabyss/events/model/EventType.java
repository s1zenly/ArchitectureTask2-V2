package com.cinemaabyss.events.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EventType {

    USER_CREATED("USER_CREATED"),
    USER_UPDATED("USER_UPDATED"),
    USER_DELETE("USER_DELETE"),
    PAYMENT_CREATED("PAYMENT_CREATED"),
    PAYMENT_COMPLETED("PAYMENT_COMPLETED"),
    PAYMENT_FAILED("PAYMENT_FAILED"),
    MOVIE_CREATED("MOVIE_CREATED"),
    MOVIE_UPDATED("MOVIE_UPDATED"),
    MOVIE_DELETED("MOVIE_DELETED");

    private final String value;

    EventType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static EventType fromValue(String value) {
        for (EventType type : EventType.values()) {
            if (type.value.equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown event type: " + value);
    }
}
