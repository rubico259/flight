package com.rubico.flight.resource;

import com.rubico.flight.domain.Coupon;
import com.rubico.flight.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<Coupon> getValidCoupon(@NotNull @RequestParam Integer couponId, @NotNull @RequestParam Double price) {
        Coupon available = couponService.getValidCoupon(couponId, price);
        return ResponseEntity.ok().body(available);
    }
}
