package com.bridgelabz.onlinebookstore.services;

import com.bridgelabz.onlinebookstore.exception.BookStoreException;
import com.bridgelabz.onlinebookstore.exception.UserException;
import com.bridgelabz.onlinebookstore.exception.WishListItemsException;
import com.bridgelabz.onlinebookstore.model.*;
import com.bridgelabz.onlinebookstore.repository.*;
import com.bridgelabz.onlinebookstore.utils.Token;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

//    @Autowired
//    BookRepository bookRepository;

    @Autowired
    BookCartRepository bookCartRepository;



    @Override
    public String addToWishList(UUID book, String token) {
//        UUID userId = jwtToken.decodeJWT(token);
//        Optional<BookCartDetails> findbookById = bookCartRepository.findByBookDetailsModelBookId(book);
//        if (!findbookById.isPresent()){
//            throw new BookStoreException(BookStoreException.ExceptionTypes.BOOK_NOT_FOUND);
//        }
//        WishList wishList = new WishList();
//        WishListItems wishListItems = new WishListItems(book, wishList);
//        List<WishListItems>  wishListItemsList = new ArrayList<>();
//        wishListItemsList.add(wishListItems);
//
//
//        wishListRespository.save(wishList);
//
//
//        wishListItemsRepository.save(wishListItems);
        return "Book Added To Wish List Successfully";
    }



    @Override
    public List<WishListItems> fetchWishList(String token) {
        UUID userId = jwtToken.decodeJWT(token);


        return null;
    }

    @Override
    public String deleteBookFromWishList(UUID wishListId, String token) {
        return null;
    }
}
