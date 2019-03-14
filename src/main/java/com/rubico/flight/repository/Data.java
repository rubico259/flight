package com.rubico.flight.repository;

import com.rubico.flight.domain.Baggage;
import com.rubico.flight.domain.Destination;
import com.rubico.flight.domain.Ticket;

import java.util.HashMap;
import java.util.Map;

public class Data {
    private static Map<Integer, Ticket> tickets;
    private static Map<Integer, Destination> destinations;
    private static Map<Integer, Baggage> baggages;


    static {
        tickets = new HashMap<>();
        tickets.put(1, new Ticket(1, true));
        tickets.put(2, new Ticket(2, false));
        tickets.put(3, new Ticket(3, false));

        destinations = new HashMap<>();
        destinations.put(1, new Destination(1, "USA"));
        destinations.put(2, new Destination(2, "Colombia"));

        baggages = new HashMap<>();
        baggages.put(1, new Baggage("1", 23.40, 40.5));
        baggages.put(2, new Baggage("1", 21.40, 30.5));
    }

    public Ticket getTicketById(Integer id) {
        return tickets.get(id);
    }

    public Destination getDestinationById(Integer id) {
        return destinations.get(id);
    }

    public Baggage getBaggageById(String id) {
        return baggages.get(id);
    }


}
