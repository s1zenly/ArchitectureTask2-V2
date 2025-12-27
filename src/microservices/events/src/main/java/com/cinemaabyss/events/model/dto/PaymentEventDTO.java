package com.cinemaabyss.events.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaymentEventDTO {

    @NotBlank
    @JsonProperty("payment_id")
    private String paymentId;

    @NotBlank
    @JsonProperty("user_id")
    private String userId;

    @NotNull
    @JsonProperty
    private BigDecimal amount;

    @NotBlank
    @JsonProperty
    private String status;

    @NotNull
    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime timestamp;

    @NotBlank
    @JsonProperty("method_type")
    private String methodType;
}
