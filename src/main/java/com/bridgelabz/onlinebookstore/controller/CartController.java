package com.bridgelabz.onlinebookstore.controller;

import com.bridgelabz.onlinebookstore.dto.CartDto;
import com.bridgelabz.onlinebookstore.dto.ResponseDto;
import com.bridgelabz.onlinebookstore.model.BookCartDetails;
import com.bridgelabz.onlinebookstore.services.CartService;
import com.bridgelabz.onlinebookstore.utils.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cart")
public class CartController {

//    @Autowired
//    CartService cartService;
//
//    @Autowired
//    Token token;
//
////    @PostMapping("/cart")
////    public ResponseEntity<ResponseDto> addBooks(@Valid @RequestBody CartDto cartDto, @RequestHeader(value = "token") String token, BindingResult bindingResult) {
////        if (bindingResult.hasErrors()) {
////            return new ResponseEntity(bindingResult.getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);
////        }
////        String message = cartService.addToCart(cartDto,token);
////        ResponseDto responseDto = new ResponseDto(message);
////        return new ResponseEntity<>(responseDto, HttpStatus.OK);
////    }
//
//    @GetMapping("/allBooks/")
//    public ResponseEntity<ResponseDto> fetchAllBooks(@RequestHeader(value = "token") String token) {
//        List<BookCartDetails> list =  cartService.allCartItems(token);
//        ResponseDto responseDTO = new ResponseDto("Response Successful", list);
//        return new ResponseEntity(responseDTO, new HttpHeaders(), HttpStatus.OK);
//    }
//
//    @PutMapping("/update/")
//    public ResponseEntity updateBookQuantity(@Valid @RequestBody CartDto cartDto, @RequestHeader(value = "token") String token) {
//        String message = cartService.updateQuantityAndPrice(cartDto,token);
//        ResponseDto responseDto = new ResponseDto(message);
//        return new ResponseEntity<>(responseDto, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/cart/{id}")
//    public ResponseEntity deleteBook(@PathVariable UUID id) {
//        String message = cartService.deleteCartItem(id);
//        ResponseDto responseDto = new ResponseDto(message);
//        return new ResponseEntity(responseDto, HttpStatus.OK);
//    }
}
