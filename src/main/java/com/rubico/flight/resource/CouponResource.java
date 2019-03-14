package com.rubico.flight.resource;

import com.rubico.flight.service.CouponService;
import com.rubico.flight.domain.Coupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api")
public class CouponResource {

    private CouponService couponService;

    @Autowired
    public CouponResource(CouponService couponService) {
        this.couponService = couponService;
    }

    @GetMapping("/coupon/iscouponvalid")
    public ResponseEntity<Coupon> isValid(@NotNull @PathVariable Integer couponId, @NotNull @PathVariable Double price) {
        Coupon available = couponService.getValidCoupon(couponId, price);
        return ResponseEntity.ok().body(available);
    }
}
