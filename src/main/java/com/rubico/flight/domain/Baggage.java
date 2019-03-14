package com.rubico.flight.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Baggage {

    @NotNull
    private String id;
    private Double wight;
    private Double height;

    public Baggage(@NotNull String id, Double wight, Double height) {
        this.id = id;
        this.wight = wight;
        this.height = height;
    }
}
