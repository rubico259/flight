package com.rubico.flight.service;

import com.rubico.flight.domain.Coupon;

public interface CouponService {


    Boolean isCouponValid(Integer couponId);

    Coupon getValidCoupon(Integer couponId, Double price);
}
