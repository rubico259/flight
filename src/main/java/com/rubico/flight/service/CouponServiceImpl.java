package com.rubico.flight.service;

import com.rubico.flight.cache.Cache;
import com.rubico.flight.domain.Coupon;
import com.rubico.flight.repository.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CouponServiceImpl implements CouponService {

    public static final String VALID_COUPON = "validCoupon";
    private final Data data;
    private Cache<Map.Entry<Integer, String>, Coupon> cache;

    @Autowired
    public CouponServiceImpl(Data data, Cache<Map.Entry<Integer, String>, Coupon> cache) {
        this.data = data;
        this.cache = cache;
    }

    @Override
    public Boolean isCouponValid(Integer couponId) {
        return data.getCoupons().contains(couponId);
    }

    @Override
    public Coupon getValidCoupon(Integer couponId, Double price) {
        try {
            if (isCouponValid(couponId)) {
                Coupon coupon = cache.get(new AbstractMap.SimpleEntry<>(couponId, VALID_COUPON));
                if (coupon != null) {
                    return coupon;
                } else {
                    coupon = new Coupon(randomizeDiscount(price), true);
                    cache.put(new AbstractMap.SimpleEntry<>(couponId, VALID_COUPON), coupon);
                    return coupon;
                }
            }
            return new Coupon(false);
        } catch (Exception e) {

        }
        return null;
    }

    private Double randomizeDiscount(Double price) {
        List<Integer> discountList = Arrays.asList(10, 50, 60);
        return price * discountList.get(new Random().nextInt(discountList.size())) / 100;
    }


}
