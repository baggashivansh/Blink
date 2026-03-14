package com.shivansh.blink.dto;

import java.time.LocalDateTime;

public class UrlStats {

    private final String originalUrl;
    private final int clickCount;
    private final LocalDateTime createdAt;

    public UrlStats(String originalUrl, int clickCount, LocalDateTime createdAt) {
        this.originalUrl = originalUrl;
        this.clickCount = clickCount;
        this.createdAt = createdAt;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public int getClickCount() {
        return clickCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}