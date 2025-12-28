package com.cinemaabyss.events.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaymentEventDTO {

    @JsonProperty("payment_id")
    private String paymentId;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty
    private BigDecimal amount;

    @JsonProperty
    private String status;

    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime timestamp;

    @JsonProperty("method_type")
    private String methodType;
}
