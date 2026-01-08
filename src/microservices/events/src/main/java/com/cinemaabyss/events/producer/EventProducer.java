package com.cinemaabyss.events.producer;

import com.cinemaabyss.events.model.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RequiredArgsConstructor
public abstract class EventProducer {

    private final KafkaTemplate<String, Event> kafkaTemplate;

    protected abstract String getTopic();

    public void send(Event event) {
        CompletableFuture<SendResult<String, Event>> future = kafkaTemplate.send(getTopic(), event.getId(), event);

        future.whenComplete((result, ex) -> {
           if (ex == null) {
               log.info("Event sent successfully - Topic: {}, Key: {}, Offset: {}, Partition: {}",
                       getTopic(), event.getId(),
                       result.getRecordMetadata().offset(),
                       result.getRecordMetadata().partition()
               );
           } else {
               log.error("Failed to send event - Topic: {}, Key: {}, Error: {}",
                       getTopic(), event.getId(), ex.getMessage()
               );
           }
        });
    }
}
