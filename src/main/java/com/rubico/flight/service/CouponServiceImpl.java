package com.rubico.flight.service;

import com.rubico.flight.domain.Coupon;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class CouponServiceImpl implements CouponService {
    @Override
    public Boolean isCouponValid(Integer couponId) {
        return null;
    }

    @Override
    public Coupon getValidCoupon(Integer couponId, Double price) {
        if (isCouponValid(couponId)) {
            new Coupon(randomizeDiscount(price), true);
        }
        return new Coupon(false);
    }

    private Double randomizeDiscount(Double price) {
        List<Integer> discountList = Arrays.asList(10, 50, 60);
        return price * discountList.get(new Random().nextInt(discountList.size())) / 100;
    }


}
