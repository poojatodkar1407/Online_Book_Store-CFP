package com.bridgelabz.onlinebookstore.services;

import com.bridgelabz.onlinebookstore.model.UserDetailsModel;
import com.bridgelabz.onlinebookstore.model.WishListItems;

import java.util.List;
import java.util.UUID;

public interface IWishListService {

    String addToWishList(UUID bookId, String token);

    List<WishListItems> fetchWishList(String token);

    String deleteBookFromWishList(UUID wishListId, String token);
}
