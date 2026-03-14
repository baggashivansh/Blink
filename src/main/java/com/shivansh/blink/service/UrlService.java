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

    private static final String CHARSET =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static final int CODE_LENGTH = 6;

    private final Random random = new Random();

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String shortenUrl(String originalUrl) {

        String shortCode;

        do {
            shortCode = generateCode();
        } while (urlRepository.findByShortCode(shortCode).isPresent());

        Url url = new Url(originalUrl, shortCode);

        urlRepository.save(url);

        return shortCode;
    }

    public Optional<Url> getOriginalUrl(String shortCode) {

        Optional<Url> urlOptional = urlRepository.findByShortCode(shortCode);

        urlOptional.ifPresent(url -> {

            url.incrementClicks();

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

        StringBuilder code = new StringBuilder();

        for (int i = 0; i < CODE_LENGTH; i++) {
            code.append(CHARSET.charAt(random.nextInt(CHARSET.length())));
        }

        return code.toString();
    }
}