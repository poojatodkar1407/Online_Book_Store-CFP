package com.bridgelabz.onlinebookstore.services;

import com.bridgelabz.onlinebookstore.model.BookCartDetails;
import com.bridgelabz.onlinebookstore.repository.BookCartRepository;
import com.bridgelabz.onlinebookstore.repository.BookRepository;
import com.bridgelabz.onlinebookstore.repository.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CartService implements ICartService {

    @Autowired
    BookCartRepository bookCartRepository;

    @Override
    public String deleteCartItem(Integer cartId) {
        bookCartRepository.deleteById(cartId);
        return "Book Has Been Deleted";
    }


}
