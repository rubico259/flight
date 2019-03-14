package com.rubico.flight.service;

import com.rubico.flight.Cache;
import com.rubico.flight.repository.Data;
import org.springframework.stereotype.Service;

@Service
public class BaggageServiceImpl implements BaggageService {


    private Data data;

    public BaggageServiceImpl(Data data) {
        this.data = data;
    }

    @Override
    public Boolean isBaggageCheckInSucceeded(Integer destinationId, String baggageId) {
        if (data.getDestinationById(destinationId).equals("USA")) {
            if (data.getBaggageById(baggageId).getWight() > 50) {
                return true;
            }
        }
        return false;
    }
}
