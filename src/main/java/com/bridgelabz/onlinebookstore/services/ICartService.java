package com.bridgelabz.onlinebookstore.services;
import com.bridgelabz.onlinebookstore.dto.CartDto;
import com.bridgelabz.onlinebookstore.dto.UpDateCartDto;
import com.bridgelabz.onlinebookstore.dto.UpdateCartDetailDto;
import com.bridgelabz.onlinebookstore.model.BookCartDetails;
import com.bridgelabz.onlinebookstore.model.BookCartSummary;
import com.bridgelabz.onlinebookstore.model.CartDetails;
import com.bridgelabz.onlinebookstore.model.UserDetailsModel;

import java.util.List;
import java.util.UUID;

public interface ICartService {
    String addToCart(CartDto cartDto, String token);

    List<BookCartSummary> showAllBooksInCart(String Token);

    String updateQuantityAndPrice(UpdateCartDetailDto cartDto, String token);

    String deleteCartItem(UUID id, String token);

    CartDetails setCart(UserDetailsModel userDetailsModel);

}
