package com.cinemaabyss.events.producer;

import com.cinemaabyss.events.model.Event;
import com.cinemaabyss.events.model.Topics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserEventsProducer extends EventProducer {

    public UserEventsProducer(@Autowired KafkaTemplate<String, Event> kafkaTemplate) {
        super(kafkaTemplate);
    }

    @Override
    protected String getTopic() {
        return Topics.USER_EVENTS_TOPIC;
    }
}
