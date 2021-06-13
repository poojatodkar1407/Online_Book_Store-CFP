package com.bridgelabz.onlinebookstore.services;

import com.bridgelabz.onlinebookstore.model.WishListItems;

import java.util.List;

public interface IWishListService {
    String addToWishList(Integer bookId, String token);

    List<WishListItems> fetchWishList(String token);

    String deleteBookFromWishList(Integer wishListId, String token);
}
