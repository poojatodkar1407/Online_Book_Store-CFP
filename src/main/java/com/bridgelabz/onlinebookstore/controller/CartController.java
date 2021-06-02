package com.bridgelabz.onlinebookstore.controller;


import com.bridgelabz.onlinebookstore.dto.ResponseDto;
import com.bridgelabz.onlinebookstore.services.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class CartController {

    @Autowired
    ICartService cartService;

    @DeleteMapping("/cart/{id}")
    public ResponseEntity deleteBook(@PathVariable Integer id) {
        String message = cartService.deleteCartItem(id);
        ResponseDto responseDto = new ResponseDto(message);
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

}
