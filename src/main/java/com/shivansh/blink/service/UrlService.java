package com.shivansh.blink.service;

import com.shivansh.blink.dto.UrlStats;
import com.shivansh.blink.model.Url;
import com.shivansh.blink.repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String shortenUrl(String originalUrl) {

        String shortCode = generateCode();

        Url url = new Url(originalUrl, shortCode);

        urlRepository.save(url);

        return shortCode;
    }

    public Optional<Url> getOriginalUrl(String shortCode) {

        Optional<Url> urlOptional = urlRepository.findByShortCode(shortCode);

        urlOptional.ifPresent(url -> {
            url.setClickCount(url.getClickCount() + 1);
            urlRepository.save(url);
        });

        return urlOptional;
    }

    public Optional<UrlStats> getStats(String shortCode) {

        Optional<Url> urlOptional = urlRepository.findByShortCode(shortCode);

        return urlOptional.map(url ->
                new UrlStats(
                        url.getOriginalUrl(),
                        url.getClickCount(),
                        url.getCreatedAt()
                )
        );
    }

    private String generateCode() {

        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        Random random = new Random();

        StringBuilder code = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            code.append(chars.charAt(random.nextInt(chars.length())));
        }

        return code.toString();
    }
}