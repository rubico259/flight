package com.rubico.flight.service;

import com.rubico.flight.cache.Cache;
import com.rubico.flight.repository.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.Map;

@Service
public class TicketServiceImpl implements TicketService {

    public static final String IS_AVAILABLE = "isAvailable";
    private Data data;
    Cache<Map.Entry<Integer, String>, Boolean> cache;

    @Autowired
    public TicketServiceImpl(Data data, Cache<Map.Entry<Integer, String>, Boolean> cache) {
        this.data = data;
        this.cache = cache;
    }

    @Override
    public Boolean isAvailable(Integer id) {
        try {
            Boolean available;
            available = cache.get(new AbstractMap.SimpleEntry<>(id, IS_AVAILABLE));
            if (available != null) {
                return available;
            } else {
                available = data.getTicketById(id).getAvailable();
                cache.put(new AbstractMap.SimpleEntry<>(id, IS_AVAILABLE), available);
                return available;
            }
        } catch (Exception e) {

        }
        return null;
    }
}

