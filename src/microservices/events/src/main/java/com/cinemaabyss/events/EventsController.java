package com.cinemaabyss.events;

import com.cinemaabyss.events.model.EventType;
import com.cinemaabyss.events.model.MovieEvent;
import com.cinemaabyss.events.model.PaymentEvent;
import com.cinemaabyss.events.model.UserEvent;
import com.cinemaabyss.events.model.dto.MovieEventDTO;
import com.cinemaabyss.events.model.dto.PaymentEventDTO;
import com.cinemaabyss.events.model.dto.UserEventDTO;
import com.cinemaabyss.events.producer.MovieEventsProducer;
import com.cinemaabyss.events.producer.PaymentEventsProducer;
import com.cinemaabyss.events.producer.UserEventsProducer;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@Validated
@RequestMapping("api/events")
@RequiredArgsConstructor
public class EventsController {

    private final UserEventsProducer userEventsProducer;
    private final PaymentEventsProducer paymentEventsProducer;
    private final MovieEventsProducer movieEventsProducer;

    @PostMapping("/user")
    public ResponseEntity<Map<String, Object>> createUserEvent(@Valid @RequestBody UserEventDTO requestEvent) {
        UserEvent userEvent = new UserEvent(EventType.USER_CREATED);
        userEvent.setUserId(requestEvent.getUserId());
        userEvent.setUsername(requestEvent.getUsername());
        userEvent.setAction(requestEvent.getAction());
        userEvent.setTimestamp(requestEvent.getTimestamp());

        userEventsProducer.send(userEvent);

        return ResponseEntity
                .status(HttpStatusCode.valueOf(201))
                .body(Map.of(
                        "status", "success",
                        "event_id", userEvent.getId()
                ));
    }

    @PostMapping("/payment")
    public ResponseEntity<Map<String, Object>> createPaymentEvent(@Valid @RequestBody PaymentEventDTO requestEvent) {
        PaymentEvent paymentEvent = new PaymentEvent(EventType.PAYMENT_CREATED);
        paymentEvent.setPaymentId(requestEvent.getPaymentId());
        paymentEvent.setUserId(requestEvent.getUserId());
        paymentEvent.setAmount(requestEvent.getAmount());
        paymentEvent.setStatus(requestEvent.getStatus());
        paymentEvent.setTimestamp(requestEvent.getTimestamp());
        paymentEvent.setMethodType(requestEvent.getMethodType());

        paymentEventsProducer.send(paymentEvent);

        return ResponseEntity
                .status(HttpStatusCode.valueOf(201))
                .body(Map.of(
                        "status", "success",
                        "event_id", paymentEvent.getId()
                ));
    }

    @PostMapping("/movie")
    public ResponseEntity<Map<String, Object>> createMovieEvent(@Valid @RequestBody MovieEventDTO requestEvent) {
        MovieEvent movieEvent = new MovieEvent(EventType.MOVIE_CREATED);
        movieEvent.setMovieId(requestEvent.getMovieId());
        movieEvent.setTitle(requestEvent.getTitle());
        movieEvent.setAction(requestEvent.getAction());
        movieEvent.setUserId(requestEvent.getUserId());

        movieEventsProducer.send(movieEvent);

        return ResponseEntity
                .status(HttpStatusCode.valueOf(201))
                .body(Map.of(
                        "status", "success",
                        "event_id", movieEvent.getId()
                ));
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, Boolean>> health() {
        return ResponseEntity.ok(Map.of("status", true));
    }

}
