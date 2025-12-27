package com.cinemaabyss.events.consumer;

import com.cinemaabyss.events.model.Event;
import com.cinemaabyss.events.model.Topics;
import com.cinemaabyss.events.model.UserEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserEventsConsumer extends EventConsumer {

    @KafkaListener(
            topics = Topics.USER_EVENTS_TOPIC,
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(@Payload UserEvent event, Acknowledgment ack) {
        log.debug("Начинаем обработку user event - {}", event.getId());
        super.consumeEvent(event, ack);
    }

    @Override
    protected void processEvent() {
        log.info("Успешно обработали user event!");
    }
}
