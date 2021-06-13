package com.bridgelabz.onlinebookstore.services;

import com.bridgelabz.onlinebookstore.model.WishList;
import com.bridgelabz.onlinebookstore.model.WishListItems;
import com.bridgelabz.onlinebookstore.repository.BookRepository;
import com.bridgelabz.onlinebookstore.repository.UserDetailsRepository;
import com.bridgelabz.onlinebookstore.repository.WishListItemsRepository;
import com.bridgelabz.onlinebookstore.repository.WishListRespository;
import com.bridgelabz.onlinebookstore.utils.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListService implements IWishListService {

    @Autowired
    private WishListRespository wishListRespository;

    @Autowired
    private WishListItemsRepository wishListItemsRepository;

    @Autowired
    Token jwtToken;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    BookRepository bookRepository;

    @Override
    public String addToWishList(Integer bookId, String token) {
//        WishList wishList = checkUserAndWishListIsExists(token);
        return null;
    }

//    private WishList checkUserAndWishListIsExists(String token) {
////        int userID = jwtToken.genera
//
//
//    }

    @Override
    public List<WishListItems> fetchWishList(String token) {
        return null;
    }

    @Override
    public String deleteBookFromWishList(Integer wishListId, String token) {
        return null;
    }
}
