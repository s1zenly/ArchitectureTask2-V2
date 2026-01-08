package com.cinemaabyss.proxy.conroller;

import com.cinemaabyss.proxy.service.ProxyRoutingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscriptions")
public class ProxySubscriptionsController {

    @Autowired
    private ProxyRoutingService routingService;

    @GetMapping
    public ResponseEntity<String> getAllSubscriptions() {
        return routingService.routeToMonolith("/api/subscriptions", HttpMethod.GET, null);
    }

    @GetMapping(params = "id")
    public ResponseEntity<String> getSubscriptionById(@RequestParam("id") long subscriptionId) {
        return routingService.routeToMonolith(String.format("/api/subscriptions?id=%s", subscriptionId), HttpMethod.GET, null);
    }

    @GetMapping(params = "user_id")
    public ResponseEntity<String> getSubscriptionsByUserId(@RequestParam("user_id") long userId) {
        return routingService.routeToMonolith(String.format("/api/subscriptions?user_id=%s", userId), HttpMethod.GET, null);
    }

    @PostMapping
    public ResponseEntity<String> createSubscription(@RequestBody String subscription) {
        return routingService.routeToMonolith("/api/subscriptions", HttpMethod.POST, subscription);
    }
}
