package com.rubico.flight.cache;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public final class CacheValue<V> {

    private final V value;
    private LocalDateTime lastAccessTimestamp;

    public CacheValue(V value) {
        this.value = value;
        this.lastAccessTimestamp = LocalDateTime.now();
    }
}
