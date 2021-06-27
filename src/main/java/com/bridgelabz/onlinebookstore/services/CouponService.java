package com.bridgelabz.onlinebookstore.services;

import com.bridgelabz.onlinebookstore.dto.CouponDto;
import com.bridgelabz.onlinebookstore.exception.BookStoreException;
import com.bridgelabz.onlinebookstore.exception.CouponException;
import com.bridgelabz.onlinebookstore.exception.UserException;
import com.bridgelabz.onlinebookstore.model.BookDetailsModel;
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
    public Coupons addCouponsToDatabase(CouponDto couponDto) {
        Optional<Coupons> searchByCouponTypeAndMinimumPrice =
                couponRepository.findByCouponsTypeAndAndMinimumPrice(couponDto.couponsType,couponDto.minimumPrice);
        if(searchByCouponTypeAndMinimumPrice.isPresent()){
            throw new CouponException(CouponException.ExceptionType.COUPON_ALREADY_PRESENT);
        }
        Coupons coupons = new Coupons(couponDto.getCouponsType(),
                couponDto.getDiscountPrice(),
                couponDto.getDescription(),
                couponDto.getExpireCouponDate(),
                couponDto.getMinimumPrice());
        Coupons save = couponRepository.save(coupons);
        return save;
    }

    @Override
    public List<Coupons> fetchCoupon(String token,Double totalPrice) {
        UUID userId = jwtToken.decodeJWT(token);
        List<Coupons> coupons = couponRepository.findAll();
        System.out.println("the array in the coupens "+coupons.toString());
        List<Coupons> couponsList = new ArrayList<>();
        for (Coupons coupons1 : coupons) {
            if (coupons1.minimumPrice <= totalPrice) {
                couponsList.add(coupons1);
            }
        }
        System.out.println("the array "+couponsList);
        List<CouponsDetails> couponsDetails = couponDetailsRepository.findByUser_UserId(userId);
        System.out.println("the array in the cod "+couponsDetails);
        for (CouponsDetails couponDetails1 : couponsDetails) {
            couponsList.remove(couponDetails1.coupons);
        }
        System.out.println("the array "+couponsList);
        if (coupons.isEmpty() || couponsList.isEmpty())
            throw new CouponException(CouponException.ExceptionType.COUPON_NOT_AVAILABLE);
        return couponsList;
    }


    @Override
    public Double addCoupon(String token, String coupon, Double totalPrice) {
        UUID userId = jwtToken.decodeJWT(token);

        UserDetailsModel user = userRepository.findById(userId).orElseThrow(() -> new UserException(UserException.ExceptionType.USER_NOT_FOUND));
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
