package com.bridgelabz.onlinebookstore.services;

import com.bridgelabz.onlinebookstore.dto.CartDto;

import com.bridgelabz.onlinebookstore.dto.UpDateCartDto;
import com.bridgelabz.onlinebookstore.dto.UpdateCartDetailDto;
import com.bridgelabz.onlinebookstore.exception.BookStoreException;

import com.bridgelabz.onlinebookstore.model.*;
import com.bridgelabz.onlinebookstore.repository.BookCartRepository;
import com.bridgelabz.onlinebookstore.repository.BookRepository;
import com.bridgelabz.onlinebookstore.repository.CartRepository;
import com.bridgelabz.onlinebookstore.repository.UserDetailsRepository;
import com.bridgelabz.onlinebookstore.utils.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import java.util.stream.Collectors;

@Service
public class CartService implements ICartService {


    Token jwtToken= new Token();

    @Autowired
    UserDetailsRepository userDetailsRepository;


    @Autowired
    BookCartRepository bookCartRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CartRepository cartRepository;



    @Override
    public String addToCart(CartDto cartDto, String token) {
        UUID userId = jwtToken.decodeJWT(token);

        BookDetailsModel bookById = bookRepository.
                findById(cartDto.getBookId()).
                orElseThrow(() -> new BookStoreException(BookStoreException.ExceptionTypes.BOOK_NOT_FOUND));

        CartDetails cartDetails = getCart(userId);
        BookCartDetails bookCartDetails = new BookCartDetails(cartDto);
        List<BookCartDetails> cartList = new ArrayList<>();

        cartList.add(bookCartDetails);
        cartDetails.getBookCartDetails().add(bookCartDetails);
        cartDetails.setBookCartDetails(cartList);
        cartRepository.save(cartDetails);
        bookCartDetails.setCartDetails(cartDetails);
        bookCartDetails.setBookDetailsModel(bookById);
        bookCartRepository.save(bookCartDetails);
        return "Book Added To Cart Successfully";
    }

    @Override
    public List<BookCartSummary> showAllBooksInCart(String Token) {
        UUID userId = jwtToken.decodeJWT(Token);
        System.out.println("the token present uuid "+userId);
        CartDetails cartDetails=getCart(userId);
        System.out.println("cart details after fetching "+cartDetails);
        List<BookCartDetails> bookCartDetails = bookCartRepository.findByCartDetailsCartIdAndOrderStatusIsFalse(cartDetails.getCartId());
        bookCartDetails.stream().forEach(bookCartDetails1 -> System.out.println(bookCartDetails1.toString()));
        List<BookCartSummary> collectBookCartDetails = bookCartDetails.stream()
                .map(summary -> new BookCartSummary(summary)).collect(Collectors.toList());
        System.out.println(bookCartDetails);
        return collectBookCartDetails;
    }

    @Override
    public String updateQuantityAndPrice(UpdateCartDetailDto cartDto, String token) {

        UUID userId = jwtToken.decodeJWT(token);
        Optional<UserDetailsModel> findTheExistedUser = userDetailsRepository.findById(userId);
        BookDetailsModel bookDetailsModel =new BookDetailsModel();
        BookCartDetails bookCartDetails = bookCartRepository.findByBookDetailsModelBookId(cartDto.getBookId()).
                orElseThrow(() -> new BookStoreException(BookStoreException.ExceptionTypes.BOOK_NOT_FOUND));
        bookCartDetails.setQuantity(cartDto.getQuantity());
        bookCartDetails.setTotalPrice(cartDto.totalPrice);
        return "Quantity of book and its price has updated";
    }

    @Override
    public String deleteCartItem(UUID id, String token) {
        UUID userId = jwtToken.decodeJWT(token);
        System.out.println("the token present uuid "+userId);
        Optional<UserDetailsModel> findTheExistedUser = userDetailsRepository.findById(userId);
        Optional<BookCartDetails> findbookById = bookCartRepository.findByBookDetailsModelBookId(id);
        if (!findbookById.isPresent()){
            throw new BookStoreException(BookStoreException.ExceptionTypes.BOOK_NOT_FOUND);
        }
        bookCartRepository.deleteById(findbookById.get().BookCartDetailsId);
        return "book is removed from cart";
    }

    private CartDetails getCart(UUID userId) {
        UserDetailsModel findTheExistedUser = userDetailsRepository.findById(userId).
                orElseThrow(() ->new BookStoreException(BookStoreException.ExceptionTypes.USER_NOT_FOUND));

        CartDetails cartDetails= cartRepository.findByUserDetailsModel(findTheExistedUser).
                orElseThrow(() -> new BookStoreException(BookStoreException.ExceptionTypes.CART_NOT_PRESENT));

        System.out.println("cart details are "+cartDetails);
        return cartDetails;

    }

    @Override
    public CartDetails setCart(UserDetailsModel userDetailsModel) {
        CartDetails cartDetails = new CartDetails();
        cartDetails.setUserDetailsModel(userDetailsModel);
        cartRepository.save(cartDetails);
        return cartDetails;
    }


}