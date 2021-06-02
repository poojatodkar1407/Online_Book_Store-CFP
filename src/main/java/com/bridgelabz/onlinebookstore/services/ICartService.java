package com.bridgelabz.onlinebookstore.services;
import com.bridgelabz.onlinebookstore.dto.CartDto;
import com.bridgelabz.onlinebookstore.dto.UpDateCartDto;
import com.bridgelabz.onlinebookstore.model.BookCartDetails;


import java.util.List;
import java.util.UUID;

public interface ICartService {
    String addToCart(CartDto cartDto, String token);

    String deleteCartItem(UUID id, String token);
    List<BookCartDetails> showAllBooksInCart(String Token);

    String updateQuantityAndPrice(UpDateCartDto upDateCartDto, String token);








}
