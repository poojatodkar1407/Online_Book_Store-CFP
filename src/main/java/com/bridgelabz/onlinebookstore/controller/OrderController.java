package com.bridgelabz.onlinebookstore.controller;

import com.bridgelabz.onlinebookstore.dto.ResponseDto;
import com.bridgelabz.onlinebookstore.model.CartDetails;
import com.bridgelabz.onlinebookstore.model.OderDetailsModel;
//import com.bridgelabz.onlinebookstore.services.IOrderService;
import com.bridgelabz.onlinebookstore.services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@ComponentScan
@EnableAutoConfiguration
public class OrderController {
    @Autowired
    IOrderService orderService;

    @PostMapping("/addorder/{totalPrice}")
    public ResponseEntity<ResponseDto>  addAnOrder(@PathVariable Double totalPrice,
                                                   @RequestHeader(value = "token", required = false) String token){

        String oderDetailsModelMessage=orderService.placeAnOrder(totalPrice,token);
        return new ResponseEntity (new ResponseDto("Order Placed succesFully : ",
                "200",oderDetailsModelMessage),
                HttpStatus.CREATED);

    }

    @GetMapping("/getall_order_details")
    public ResponseEntity<List<OderDetailsModel>> fetchAllOrderOfParticularUser(@RequestHeader(value = "token", required = false) String token){

        return ResponseEntity.status(HttpStatus.OK).body(orderService.getAllOrders(token));
    }
}
