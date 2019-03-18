package com.rubico.flight.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;


@Service
public class Cache<K, V> implements ICache<K, V> {

    private final Map<K, CacheValue<V>> cacheMap = new HashMap<>();
    private static long ttlInSec;
    private static AtomicReference<Cache> INSTANCE = new AtomicReference<>();


    @Autowired
    public Cache(@Value("${cache.expire}") long ttlInSec) {
        final Cache previous = INSTANCE.getAndSet(this);
        if (previous != null)
            throw new IllegalStateException("Second singleton " + this + " created after " + previous);
        this.ttlInSec = ttlInSec;
    }


    @Override
    public V get(K key) {
        CacheValue<V> vCacheValue = cacheMap.get(key);
        if (cacheMap.size() > 0 && vCacheValue != null) {
            CacheValue<V> cacheValue = vCacheValue;
            if (ChronoUnit.SECONDS.between(cacheValue.getLastAccessTimestamp(), LocalDateTime.now()) > ttlInSec) {
                cacheMap.remove(key);
                return null;
            } else {
                return cacheValue.getValue();
            }
        } else {
            return null;
        }
    }

    @Override
    public void put(K key, V value) {
        cacheMap.put(key, new CacheValue<V>(value));
    }
}



