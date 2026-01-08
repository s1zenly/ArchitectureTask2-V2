package com.cinemaabyss.proxy.conroller;

import com.cinemaabyss.proxy.service.ProxyRoutingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class ProxyPaymentsController {

    @Autowired
    private ProxyRoutingService routingService;

    @GetMapping
    public ResponseEntity<String> getAllPayments() {
        return routingService.routeToMonolith("/api/payments", HttpMethod.GET, null);
    }

    @GetMapping(params = "id")
    public ResponseEntity<String> getPaymentById(@RequestParam("id") long paymentId) {
        return routingService.routeToMonolith(String.format("/api/payments?id=%s", paymentId), HttpMethod.GET, null);
    }

    @GetMapping(params = "user_id")
    public ResponseEntity<String> getPaymentsByUserId(@RequestParam("user_id") long userId) {
        return routingService.routeToMonolith(String.format("/api/payments?user_id=%s", userId), HttpMethod.GET, null);
    }

    @PostMapping
    public ResponseEntity<String> createPayment(@RequestBody String payment) {
        return routingService.routeToMonolith("/api/payments", HttpMethod.POST, payment);
    }
}
