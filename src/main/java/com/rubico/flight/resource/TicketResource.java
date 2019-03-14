package com.rubico.flight.resource;

import com.rubico.flight.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api")
public class TicketResource {

    private TicketService ticketService;

    @Autowired
    public TicketResource(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/ticket/isavailableticket/{ticketId}")
    public ResponseEntity<Boolean> isAvailable(@NotNull @PathVariable Integer ticketId) {
        Boolean available = ticketService.isAvailable(ticketId);
        return ResponseEntity.ok().body(available);
    }


}
