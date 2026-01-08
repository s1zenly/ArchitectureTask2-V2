package com.cinemaabyss.proxy;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ProxyAppConfig {

    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(5000);
        factory.setReadTimeout(10000);

        return new RestTemplate(factory);
    }

    @Data
    @Component
    public static class ProxyProperties {

        @Value("${MONOLITH_URL}")
        private String monolithUrl;

        @Value("${MOVIES_SERVICE_URL}")
        private String moviesServiceUrl;

        @Value("${GRADUAL_MIGRATION}")
        private boolean gradualMigration;

        @Value("${MOVIES_MIGRATION_PERCENT}")
        private int moviesMigrationPercent;
    }
}