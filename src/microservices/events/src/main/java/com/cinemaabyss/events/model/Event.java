package com.cinemaabyss.events.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Data
public abstract class Event {

    private String id;
    private EventType type;
    private LocalDateTime eventTimestamp;

    protected Event() {
        this.id = UUID.randomUUID().toString();
        this.eventTimestamp = LocalDateTime.now(Clock.system(ZoneId.of("UTC")));
    }

    protected Event(EventType type) {
        this();
        this.type = type;
    }
}
