package com.cinemaabyss.events.consumer;

import com.cinemaabyss.events.model.Event;
import com.cinemaabyss.events.model.PaymentEvent;
import com.cinemaabyss.events.model.Topics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentEventsConsumer extends EventConsumer {

    @KafkaListener(
            topics = Topics.PAYMENT_EVENTS_TOPIC,
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(@Payload PaymentEvent event, Acknowledgment ack) {
        log.debug("Начинаем обработку payment event - {}", event.getId());
        super.consumeEvent(event, ack);
    }

    @Override
    protected void processEvent() {
        log.info("Успешно обработали payment event!");
    }
}
