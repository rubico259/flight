package com.rubico.flight.service;

import com.rubico.flight.repository.Data;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {

    Data data = new Data();

    @Override
    public Boolean isAvailable(Integer id) {
        try {
            return data.getTicketById(id).getAvailable();
        } catch (Exception e) {

        }
        return null;
    }
}

