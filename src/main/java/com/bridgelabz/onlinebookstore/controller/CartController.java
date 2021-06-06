package com.bridgelabz.onlinebookstore.controller;

import com.bridgelabz.onlinebookstore.dto.CartDto;
import com.bridgelabz.onlinebookstore.dto.ResponseDto;

import com.bridgelabz.onlinebookstore.dto.UpDateCartDto;
import com.bridgelabz.onlinebookstore.model.BookCartDetails;
import com.bridgelabz.onlinebookstore.model.CartDetails;
import com.bridgelabz.onlinebookstore.services.ICartService;
import com.bridgelabz.onlinebookstore.utils.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cart")
@ComponentScan
@EnableAutoConfiguration
public class CartController {

    @Autowired
    ICartService cartService;


    Token token = new Token();

    @PostMapping("/addtocart")
    public ResponseEntity<ResponseDto> addBookToCart(@RequestBody CartDto cartDto,
                                                     @RequestHeader(value = "token", required = false) String token) {
        String message = cartService.addToCart(cartDto, token);
        return new ResponseEntity (new ResponseDto(message,
                "200",null),
                HttpStatus.CREATED);

    }

    @GetMapping("/allbooksincart")
    public ResponseEntity<List<CartDetails>> getAllBooksInCart(@RequestHeader(value = "token", required = false) String Token) {

        return ResponseEntity.status(HttpStatus.OK).body(cartService.showAllBooksInCart(Token));

    }

    @DeleteMapping("/cart/{id}")
    public ResponseEntity<ResponseDto> deleteBook(@PathVariable UUID id,
                                     @RequestHeader(value = "token", required = false) String Token) {
        String message = cartService.deleteCartItem(id, Token);
        ResponseDto responseDto = new ResponseDto(message);
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

    @PutMapping("/updatecartofbook")
    public ResponseEntity updateBookQuantity(@Valid @RequestBody CartDto cartDto,
                                             @RequestHeader(value = "token") String token) {
        String message = cartService.updateQuantityAndPrice(cartDto, token);
        ResponseDto responseDto = new ResponseDto(message);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


}

