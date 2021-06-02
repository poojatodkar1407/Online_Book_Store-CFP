package com.bridgelabz.onlinebookstore.services;

import com.bridgelabz.onlinebookstore.dto.CartDto;
import com.bridgelabz.onlinebookstore.dto.ResponseDto;
import com.bridgelabz.onlinebookstore.exception.CartException;
import com.bridgelabz.onlinebookstore.model.BookCartDetails;

import java.util.List;
import java.util.UUID;

public interface ICartService {
    String addToCart(CartDto cartDto, String token);

    List<BookCartDetails> allCartItems(String token);

    String updateQuantityAndPrice(CartDto cartDto, String token);

    String deleteCartItem(UUID id);

}
