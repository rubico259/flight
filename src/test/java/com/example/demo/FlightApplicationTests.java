package com.example.demo;

import com.rubico.flight.domain.Coupon;
import com.rubico.flight.repository.Data;
import com.rubico.flight.service.CouponService;
import com.rubico.flight.service.CouponServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class FlightApplicationTests {

    private CouponService couponService;

    @Before
    public void init() {
        couponService = new CouponServiceImpl(new Data());
    }

    @Test
    public void couponTest() {
        couponService = new CouponServiceImpl(new Data());
        Coupon coupon = couponService.getValidCoupon(1, 200.56);

        Assert.assertEquals(new Coupon(false), coupon);
    }

}
