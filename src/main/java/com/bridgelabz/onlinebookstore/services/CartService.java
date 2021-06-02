package com.bridgelabz.onlinebookstore.services;

import com.bridgelabz.onlinebookstore.dto.CartDto;
import com.bridgelabz.onlinebookstore.dto.ResponseDto;
import com.bridgelabz.onlinebookstore.exception.CartException;
import com.bridgelabz.onlinebookstore.exception.UserException;
import com.bridgelabz.onlinebookstore.model.BookCartDetails;
import com.bridgelabz.onlinebookstore.model.BookDetailsModel;
import com.bridgelabz.onlinebookstore.model.CartDetails;
import com.bridgelabz.onlinebookstore.model.UserDetailsModel;
import com.bridgelabz.onlinebookstore.repository.BookCartRepository;
import com.bridgelabz.onlinebookstore.repository.BookRepository;
import com.bridgelabz.onlinebookstore.repository.CartRepository;
import com.bridgelabz.onlinebookstore.repository.UserDetailsRepository;
import com.bridgelabz.onlinebookstore.utils.MailService;
import com.bridgelabz.onlinebookstore.utils.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartService implements ICartService {

//    @Autowired
//    MailService mailService;
//
//    @Autowired
//    private CartRepository cartRepository;
//
//    @Autowired
//    private UserDetailsRepository userDetailsRepository;
//
//    @Autowired
//    private BookRepository bookRepository;
//
//    @Autowired
//    private BookCartRepository bookCartRepository;
//
//    @Autowired
//    Token jwtToken;
//
//    @Override
//    public String addToCart(CartDto cartDto, String token) {
////        UUID userId = jwtToken.decodeJWT(token);
////        CartDetails cartDetails = getCart(userId);
////        BookCartDetails bookCartDetails = new BookCartDetails(cartDto);
////        BookDetailsModel books = bookRepository.findById(cartDto.cartId).get();
////        List<BookCartDetails> cartList = new ArrayList<>();
////        cartList.add(bookCartDetails);
////        cartDetails.getBookCartDetails().add(bookCartDetails);
////        cartDetails.setBookCartDetails(cartList);
////        cartRepository.save(cartDetails);
////        bookCartDetails.setCartDetails(cartDetails);
////        bookCartDetails.setBookDetailsModel(books);
////        bookCartRepository.save(bookCartDetails);
//        return "Book Added To Cart Successfully";
//    }
//
//    public CartDetails getCart(UUID userId) {
//        UserDetailsModel user = userDetailsRepository.findById(userId).orElseThrow(() -> new UserException("User Not Found", UserException.ExceptionType.INVALID_DATA));
//        CartDetails cartDetails = cartRepository.findByUserDetailsModel(user).orElseThrow(() -> new CartException("Cart Not Found"));
//        return cartDetails;
//    }
//
//    @Override
//    public List<BookCartDetails> allCartItems(String token) {
//        UUID userId = jwtToken.decodeJWT(token);
//        CartDetails cartDetails = getCart(userId);
//        List<BookCartDetails> bookCartDetails = bookCartRepository.getCartItems(cartDetails.getCartId());
//        return bookCartDetails;
//    }
//
//    @Override
//    public String updateQuantityAndPrice(CartDto cartDto, String token) {
//        UUID verifyToken = jwtToken.decodeJWT(token);
//        userDetailsRepository.findById(verifyToken);
//
//        BookCartDetails bookCartDetails = bookCartRepository.findById(cartDto.cartId).get();
//        bookCartDetails.setQuantity(cartDto.quantity);
//        bookCartDetails.setTotalPrice(cartDto.totalPrice);
//        bookCartRepository.save(bookCartDetails);
//        return "Book Quantity and price updated";
//
//    }
//
//    @Override
//    public String deleteCartItem(UUID cartId) {
////        bookCartRepository.deleteById(cartId);
//        return "Book Has Been Deleted";
//    }

}
