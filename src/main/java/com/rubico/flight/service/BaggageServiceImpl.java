package com.rubico.flight.service;

import com.rubico.flight.cache.Cache;
import com.rubico.flight.repository.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaggageServiceImpl implements BaggageService {

    private Data data;
    private Cache<String, Boolean> cache;

    @Autowired
    public BaggageServiceImpl(Data data, Cache<String, Boolean> cache) {
        this.data = data;
        this.cache = cache;
    }

    @Override
    public Boolean isBaggageCheckInSucceeded(Integer destinationId, String baggageId) {
        try {
            if (data.getDestinationById(destinationId).equals("USA")) {
                if (data.getBaggageById(baggageId).getWight() > 50) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {

        }
        return null;
    }
}
