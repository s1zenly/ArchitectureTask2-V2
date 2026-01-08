package com.cinemaabyss.proxy.conroller;

import com.cinemaabyss.proxy.service.ProxyRoutingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class ProxyUsersController {

    @Autowired
    private ProxyRoutingService routingService;

    @GetMapping
    public ResponseEntity<String> getAllUsers() {
        return routingService.routeToMonolith("/api/users", HttpMethod.GET, null);
    }

    @GetMapping(params = "id")
    public ResponseEntity<String> getUserById(@RequestParam("id") long userId) {
        return routingService.routeToMonolith(String.format("/api/users?id=%s", userId), HttpMethod.GET, null);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody String user) {
        return routingService.routeToMonolith("/api/users", HttpMethod.POST, user);
    }
}