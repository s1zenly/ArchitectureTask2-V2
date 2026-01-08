package com.cinemaabyss.proxy.conroller;


import com.cinemaabyss.proxy.ProxyAppConfig;
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
@RequestMapping("/api/movies")
public class ProxyMoviesController {

    @Autowired
    private ProxyAppConfig.ProxyProperties proxyProperties;

    @Autowired
    private ProxyRoutingService routingService;

    @GetMapping
    public ResponseEntity<String> getAllMovies() {
        return routingService.route(
                proxyProperties.getMoviesServiceUrl(),
                "/api/movies", HttpMethod.GET, null,
                proxyProperties.isGradualMigration(),
                proxyProperties.getMoviesMigrationPercent()
        );
    }

    @GetMapping(params = "id")
    public ResponseEntity<String> getMovieById(@RequestParam(name = "id") long movieId) {
        return routingService.route(
                proxyProperties.getMoviesServiceUrl(),
                String.format("/api/movies?id=%s", movieId), HttpMethod.GET, null,
                proxyProperties.isGradualMigration(),
                proxyProperties.getMoviesMigrationPercent()
        );
    }

    @PostMapping
    public ResponseEntity<String> createMovie(@RequestBody String movie) {
        return routingService.route(
                proxyProperties.getMoviesServiceUrl(),
                "/api/movies", HttpMethod.POST, movie,
                proxyProperties.isGradualMigration(),
                proxyProperties.getMoviesMigrationPercent()
        );
    }
}
