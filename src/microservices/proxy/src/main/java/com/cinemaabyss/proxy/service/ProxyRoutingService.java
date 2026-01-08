package com.cinemaabyss.proxy.service;

import java.util.Random;

import com.cinemaabyss.proxy.ProxyAppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProxyRoutingService {

    private static final Random random = new Random();

    @Autowired
    private ProxyAppConfig.ProxyProperties properties;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<String> route(String basePath, String path, HttpMethod httpMethod, String body,
                                        boolean isMigration, int migrationPercent) {
        if (isMigration && isMigrationGroup(migrationPercent)) {
            return routeToMicroservice(basePath.concat(path), httpMethod, body);
        }
        return routeToMonolith(path, httpMethod, body);
    }

    public ResponseEntity<String> routeToMicroservice(String path, HttpMethod httpMethod, String body) {
        return execute(path, httpMethod, body);
    }

    public ResponseEntity<String> routeToMonolith(String path, HttpMethod httpMethod, String body) {
        String fullPath = properties.getMonolithUrl().concat(path);
        return execute(fullPath, httpMethod, body);
    }

    private ResponseEntity<String> execute(String path, HttpMethod method, String body) {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(body, httpHeaders);

            return restTemplate.exchange(path, method, request, String.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isMigrationGroup(int migrationPercent) {
        int randomInt = random.nextInt(0, 101);
        return randomInt > migrationPercent;
    }

}
