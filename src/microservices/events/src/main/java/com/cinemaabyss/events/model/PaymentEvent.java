package com.cinemaabyss.events.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class PaymentEvent extends Event {

    private String paymentId;
    private String userId;
    private BigDecimal amount;
    private String status;
    private LocalDateTime timestamp;
    private String methodType;

    public PaymentEvent(EventType type) {
        super(type);
    }
}
