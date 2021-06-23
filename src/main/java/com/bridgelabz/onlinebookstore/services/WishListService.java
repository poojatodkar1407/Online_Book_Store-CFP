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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookCartRepository bookCartRepository;



    @Override
    public String addToWishList(UUID book, String token) {
        UUID userId = jwtToken.decodeJWT(token);
        BookDetailsModel bookById = bookRepository.
                findById(book).
                orElseThrow(() -> new BookStoreException(BookStoreException.ExceptionTypes.BOOK_NOT_FOUND));

        if(bookById.isAdded()==true){
            throw new BookStoreException(BookStoreException.ExceptionTypes.BOOK_ALREADY_PRESENT_IN_CART);
        }

        else {
            if (bookById.isAddedToWish() == true) {
                throw new BookStoreException(BookStoreException.ExceptionTypes.BOOK_AlREADY_PRESENT);
            } else {

                WishList wishList = getWish(userId);
                WishListItems wishListItems = new WishListItems(book);
                List<WishListItems> wishListItemsList = new ArrayList<>();
                wishListItemsList.add(wishListItems);
                wishList.getWishListItems().add(wishListItems);
                wishList.setWishListItems(wishListItemsList);
                wishListRespository.save(wishList);
                wishListItems.setWishList(wishList);
                wishListItems.setBook(bookById);
                // wishListItems.setAddedToWishtDate(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MMM-yy")));
                wishListItemsRepository.save(wishListItems);
                bookById.setAddedToWish(true);
                bookRepository.save(bookById);

                return "Book Added To Wish List Successfully";
            }
        }
    }

    private WishList getWish(UUID userId) {
        UserDetailsModel findTheExistedUser = userDetailsRepository.findById(userId).
                orElseThrow(() ->new BookStoreException(BookStoreException.ExceptionTypes.USER_NOT_FOUND));

        WishList wishList = wishListRespository.findByUser(findTheExistedUser).
                orElseThrow(() -> new BookStoreException(BookStoreException.ExceptionTypes.WISH_LIST_IS_NOT_PRESENT));

        return wishList;
    }

    @Override
    public WishList setWish (UserDetailsModel userDetailsModel) {
        WishList wishList= new WishList();
        wishList.setUser(userDetailsModel);
        wishListRespository.save(wishList);
        return wishList;
    }



    @Override
    public List<WishListItems> fetchWishList(String token) {
        UUID userId = jwtToken.decodeJWT(token);
        WishList wishList = getWish(userId);




        return null;
    }

//    @Override
//    public String deleteBookFromWishList(UUID wishListId, String token) {
//        return null;
//    }
}
