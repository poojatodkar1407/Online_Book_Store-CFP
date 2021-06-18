package com.bridgelabz.onlinebookstore.services;

import com.bridgelabz.onlinebookstore.dto.CartDto;

import com.bridgelabz.onlinebookstore.dto.UpDateCartDto;
import com.bridgelabz.onlinebookstore.dto.UpdateCartDetailDto;
import com.bridgelabz.onlinebookstore.exception.BookStoreException;

import com.bridgelabz.onlinebookstore.model.BookCartDetails;
import com.bridgelabz.onlinebookstore.model.BookDetailsModel;
import com.bridgelabz.onlinebookstore.model.CartDetails;
import com.bridgelabz.onlinebookstore.model.UserDetailsModel;
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

       UserDetailsModel findTheExistedUser = userDetailsRepository.findById(userId).
               orElseThrow(() ->new BookStoreException(BookStoreException.ExceptionTypes.USER_NOT_FOUND));

//        CartDetails cartDetails= cartRepository.findByUserDetailsModel(findTheExistedUser)
//                .orElseThrow(() -> new BookStoreException(BookStoreException.ExceptionTypes.CART_NOT_PRESENT));

       BookDetailsModel bookById = bookRepository.
                findById(cartDto.getBookId()).
                orElseThrow(() -> new BookStoreException(BookStoreException.ExceptionTypes.BOOK_NOT_FOUND));
        CartDetails cartDetailsSave = new CartDetails();
        System.out.println(bookById);

        BookCartDetails bookCartDetails = new BookCartDetails(cartDto);

        System.out.println("the book details are :"+bookCartDetails);

        List<BookCartDetails> cartList = new ArrayList<>();
        cartList.add(bookCartDetails);
       // cartDetails.getBookCartDetails().add(bookCartDetails);
//        cartDetailsSave.setBookId(cartDto.getBookId());
        cartDetailsSave.setQuantity(cartDto.getQuantity());
        cartDetailsSave.setTotalPrice(cartDto.getTotalPrice());
        //cartDetailsSave.setBookCartDetails(cartList);
        cartDetailsSave.setUserDetailsModel(findTheExistedUser);
        cartDetailsSave.setBookImage(bookById.getImage());
        cartDetailsSave.setBookName(bookById.getBookName());
        cartDetailsSave.setAuthorName(bookById.getAuthorName());
        cartDetailsSave.setBookDetailsID(bookById.getBookId().toString());


       //cartDetails.setUserDetailsModel(findTheExistedUser);
        //cartDetails.setBookCartDetails(cartList);
        cartRepository.save(cartDetailsSave);

        bookCartDetails.setBookDetailsModel(bookById);
        System.out.println("Book Cart Details : "+bookCartDetails);
        BookCartDetails saveBooksToCart = bookCartRepository.save(bookCartDetails);
        System.out.println(saveBooksToCart);
        return "Book Added To Cart Successfully";



    }

    @Override
    public String deleteCartItem(UUID id, String token) {
        UUID userId = jwtToken.decodeJWT(token);
        System.out.println("the token present uuid "+userId);
        Optional<UserDetailsModel> findTheExistedUser = userDetailsRepository.findById(userId);

        if (!findTheExistedUser.isPresent()){
            throw new BookStoreException(BookStoreException.ExceptionTypes.USER_NOT_FOUND);
        }
        BookDetailsModel bookDetailsModel = new BookDetailsModel(id);
        Optional<BookCartDetails> findbookById = bookCartRepository.findByBookDetailsModel(bookDetailsModel);

        if (!findbookById.isPresent()){
            throw new BookStoreException(BookStoreException.ExceptionTypes.CART_NOT_PRESENT);
        }
        UUID idNeeded=findbookById.get().cartDetailsId;
        bookCartRepository.deleteById(idNeeded);

        Optional<CartDetails> searchForBookInCart = cartRepository.findByBookDetailsID(id.toString());
        if(!searchForBookInCart.isPresent()){
            throw new BookStoreException(BookStoreException.ExceptionTypes.BOOK_NOT_FOUND);
        }
        UUID idNeededInCart =searchForBookInCart.get().getCartId();
        cartRepository.deleteById(idNeededInCart);



        return "book is removed from cart";
    }

    @Override
    public List<CartDetails> showAllBooksInCart(String Token) {
        UUID userId = jwtToken.decodeJWT(Token);
        System.out.println("the token present uuid "+userId);
        Optional<UserDetailsModel> findTheExistedUser = userDetailsRepository.findById(userId);


        if (!findTheExistedUser.isPresent()){
            throw new BookStoreException(BookStoreException.ExceptionTypes.USER_NOT_FOUND);
        }

        List<CartDetails> cartDetailsListOfUserModel = cartRepository.findByUserDetailsModel(findTheExistedUser.get());
        cartDetailsListOfUserModel.forEach(cartDetails -> System.out.println(cartDetails.toString()));
        return cartDetailsListOfUserModel;
    }

    @Override
    public String updateQuantityAndPrice(UpdateCartDetailDto cartDto, String token) {


        UUID userId = jwtToken.decodeJWT(token);
        Optional<UserDetailsModel> findTheExistedUser = userDetailsRepository.findById(userId);

        if (!findTheExistedUser.isPresent()){
            throw new BookStoreException(BookStoreException.ExceptionTypes.USER_NOT_FOUND);
        }
       BookDetailsModel bookDetailsModel = new BookDetailsModel(cartDto.getBookId());
     //List<BookCartDetails> searchForACart = bookCartRepository.findByBookDetailsModel(cartDto.getBookId());
        Optional<BookCartDetails> searchForACart = bookCartRepository.findByBookDetailsModel(bookDetailsModel);
        System.out.println("book search "+searchForACart);
        System.out.println(cartDto.getBookId());
        Optional<CartDetails> searchForBookInCart = cartRepository.findByBookDetailsID(cartDto.getBookId().toString());
        System.out.println(searchForBookInCart);

          if(!searchForBookInCart.isPresent()){
              throw new BookStoreException(BookStoreException.ExceptionTypes.BOOK_NOT_FOUND);
          }

        if (!searchForACart.isPresent()){
            throw new BookStoreException(BookStoreException.ExceptionTypes.CART_NOT_PRESENT);
        }


        searchForACart.get().setQuantity(cartDto.getQuantity());
        searchForACart.get().setTotalPrice(cartDto.getTotalPrice());
        BookCartDetails save = bookCartRepository.save(searchForACart.get());
//
        //searchForBookInCart.get().setBookDetailsID(cartDto.getBookId());
        searchForBookInCart.get().setQuantity(cartDto.getQuantity());
        searchForBookInCart.get().setTotalPrice(cartDto.getTotalPrice());
        CartDetails saveDetails=cartRepository.save(searchForBookInCart.get());

        return "Quantity of book and its price has updated";


    }



}