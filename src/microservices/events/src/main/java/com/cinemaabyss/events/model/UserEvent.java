package com.cinemaabyss.events.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class UserEvent extends Event {

    private String userId;
    private String username;
    private LocalDateTime timestamp;
    private String action;

    public UserEvent(EventType type) {
        super(type);
    }
}
