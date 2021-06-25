package com.bridgelabz.onlinebookstore.services;

import com.bridgelabz.onlinebookstore.dto.CouponDto;
import com.bridgelabz.onlinebookstore.model.Coupons;
import com.bridgelabz.onlinebookstore.model.CouponsDetails;

import java.util.List;

public interface ICouponService {

    Coupons addCouponsToDatabase(CouponDto couponDto);

    List<Coupons> fetchCoupon(String token, Double totalPrice);

    Double addCoupon(String token, String coupon, Double totalPrice);
}
