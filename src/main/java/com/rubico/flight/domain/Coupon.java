package com.rubico.flight.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Coupon {
    @NotNull
    private Integer id;
    private String coupon;
    private Double price;
    private Boolean valid;

    public Coupon(Double price, Boolean valid) {
        this.price = price;
        this.valid = valid;
    }

    public Coupon(Boolean valid) {
        this.valid = valid;
    }
}
