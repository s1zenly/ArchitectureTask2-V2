package com.cinemaabyss.events.consumer;

import com.cinemaabyss.events.model.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.Acknowledgment;

@Slf4j
public abstract class EventConsumer {

    protected abstract void processEvent();

    public void consumeEvent(Event event, Acknowledgment ack) {
        try {
            log.info("""
                    === EVENT CONSUMED ===
                    Type: {}
                    ID: {}
                    TIMESTAMP: {}
                    """,
                    event.getType(), event.getId(), event.getEventTimestamp()
            );

            processEvent();
            ack.acknowledge();;
        } catch (Exception e) {
            log.error("Error processing event - {}, error - {}", event.getType(), e.getMessage(), e);
        }
    }
}
