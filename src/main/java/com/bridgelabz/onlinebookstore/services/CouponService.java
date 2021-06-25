package com.bridgelabz.onlinebookstore.services;

import com.bridgelabz.onlinebookstore.exception.CouponException;
import com.bridgelabz.onlinebookstore.model.Coupons;
import com.bridgelabz.onlinebookstore.model.CouponsDetails;
import com.bridgelabz.onlinebookstore.model.UserDetailsModel;
import com.bridgelabz.onlinebookstore.repository.CouponDetailsRepository;
import com.bridgelabz.onlinebookstore.repository.CouponRepository;
import com.bridgelabz.onlinebookstore.repository.UserDetailsRepository;
import com.bridgelabz.onlinebookstore.utils.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CouponService implements ICouponService{
    @Autowired
    CouponRepository couponRepository;

    @Autowired
    Token jwtToken;

    @Autowired
    CouponDetailsRepository couponDetailsRepository;

    @Autowired
    UserDetailsRepository userRepository;


    @Override
    public List<Coupons> fetchCoupon(String token,Double totalPrice) {
        UUID userId = jwtToken.decodeJWT(token);
        List<Coupons> coupons = couponRepository.findAll();
        List<Coupons> couponsList = new ArrayList<>();
        for (Coupons coupons1 : coupons) {
            if (coupons1.minimumPrice <= totalPrice) {
                couponsList.add(coupons1);
            }
        }
        List<CouponsDetails> couponsDetails = couponDetailsRepository.findByUser_UserId(userId);
        for (CouponsDetails couponDetails1 : couponsDetails) {
            couponsList.remove(couponDetails1.coupons);
        }
        if (coupons.isEmpty() || couponsList.isEmpty())
            throw new CouponException("Coupons Not Available");
        return couponsList;
    }


    @Override
    public Double addCoupon(String token, String coupon, Double totalPrice) {
        UUID userId = jwtToken.decodeJWT(token);

        UserDetailsModel user = userRepository.findById(userId).orElseThrow(() -> new CouponException("USER NOT FOUND"));
        Optional<Coupons> coupons = couponRepository.findByCouponsType(coupon);

        if(!coupons.isPresent()){
            throw new CouponException("COUPEN NOT FOUND");
        }

        CouponsDetails couponsDetails = new CouponsDetails(coupons.get(), user);
        couponDetailsRepository.save(couponsDetails);

        Double discountPrice = (totalPrice - coupons.get().discountPrice) < 0 ? 0 : (totalPrice - coupons.get().discountPrice);
        return discountPrice;
    }
}
