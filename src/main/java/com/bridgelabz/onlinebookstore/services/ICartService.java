package com.bridgelabz.onlinebookstore.services;

import com.bridgelabz.onlinebookstore.model.BookCartDetails;
import com.bridgelabz.onlinebookstore.model.CartDetails;
import com.bridgelabz.onlinebookstore.model.UserDetailsModel;

import java.util.List;

public interface ICartService {


    String deleteCartItem(Integer id);

}
