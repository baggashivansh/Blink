package com.shivansh.blink.controller;

import com.shivansh.blink.dto.UrlStats;
import com.shivansh.blink.model.Url;
import com.shivansh.blink.service.UrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;
import java.util.Optional;

@RestController
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/api/shorten")
    public Map<String, String> shorten(@RequestBody Map<String, String> request) {

        String originalUrl = request.get("url");

        String code = urlService.shortenUrl(originalUrl);

        return Map.of(
                "shortUrl", "http://localhost:8080/" + code
        );
    }

    @GetMapping("/{code}")
    public ResponseEntity<Void> redirect(@PathVariable String code) {

        Optional<Url> urlOptional = urlService.getOriginalUrl(code);

        if (urlOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity
                .status(302)
                .location(URI.create(urlOptional.get().getOriginalUrl()))
                .build();
    }

    @GetMapping("/api/stats/{code}")
    public ResponseEntity<UrlStats> stats(@PathVariable String code) {

        Optional<UrlStats> stats = urlService.getStats(code);

        return stats
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}