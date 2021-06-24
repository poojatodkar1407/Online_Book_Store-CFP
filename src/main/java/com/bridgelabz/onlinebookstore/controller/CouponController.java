package com.bridgelabz.onlinebookstore.controller;

import com.bridgelabz.onlinebookstore.dto.ResponseDto;
import com.bridgelabz.onlinebookstore.model.Coupons;
import com.bridgelabz.onlinebookstore.services.ICouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("/coupon")
@ComponentScan
@EnableAutoConfiguration
public class CouponController {
    @Autowired
    ICouponService couponService;

    @GetMapping("/fetchOrder")
    public ResponseEntity fetchOrderCoupon(@RequestHeader(value = "token") String token, @RequestParam(name = "totalPrice") Double totalPrice) {
        List<Coupons> orders = couponService.fetchCoupon(token,totalPrice);
        ResponseDto response = new ResponseDto("Coupons Fetched Successfully", orders);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/addCoupon")
    public ResponseEntity addCoupon(@RequestHeader(value = "token") String token,@RequestParam(name = "discountCoupon",defaultValue = "0") String coupon, @RequestParam(name = "totalPrice") Double totalPrice){
        Double coupon1 = couponService.addCoupon(token, coupon, totalPrice);
        ResponseDto response = new ResponseDto("Coupon added successfully",coupon1);
        return new ResponseEntity(response,HttpStatus.OK);
    }
}
