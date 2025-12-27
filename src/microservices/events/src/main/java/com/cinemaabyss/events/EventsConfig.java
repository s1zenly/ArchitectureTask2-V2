package com.cinemaabyss.events;

import com.cinemaabyss.events.model.Topics;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class EventsConfig {

    @Bean
    public NewTopic movieEventsTopic() {
        return TopicBuilder.name(Topics.MOVIE_EVENTS_TOPIC)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic userEventsTopic() {
        return TopicBuilder.name(Topics.USER_EVENTS_TOPIC)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic paymentEventsTopic() {
        return TopicBuilder.name(Topics.PAYMENT_EVENTS_TOPIC)
                .partitions(1)
                .replicas(1)
                .build();
    }
}
