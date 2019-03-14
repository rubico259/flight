package com.rubico.flight.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Ticket {

    @NotNull
    private Integer id;
    private String name;
    private Boolean available;

    public Ticket() {
    }

    public Ticket(Integer id, Boolean available) {
        this.id = id;
        this.available = available;
    }
}
