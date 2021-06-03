package com.bridgelabz.onlinebookstore.services;

import com.bridgelabz.onlinebookstore.exception.BookStoreException;
import com.bridgelabz.onlinebookstore.model.CartDetails;
import com.bridgelabz.onlinebookstore.model.CustomerDetails;
import com.bridgelabz.onlinebookstore.model.OderDetailsModel;
import com.bridgelabz.onlinebookstore.model.UserDetailsModel;
import com.bridgelabz.onlinebookstore.repository.*;
import com.bridgelabz.onlinebookstore.utils.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService implements IOrderService{

    @Autowired
    Token jwtToken;

    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Autowired
    BookCartRepository bookCartRepository;

    @Autowired
    UserDetailsRepository userDetailsRepository;



    @Autowired
    CartRepository cartRepository;

    @Autowired
    CustomerDetailsRepository customerDetailsRepository;

    @Override
    public String placeAnOrder(Double totalPrice, String token) {
        UUID userId = jwtToken.decodeJWT(token);
        UserDetailsModel findTheExistedUser = userDetailsRepository.findById(userId).
                orElseThrow(() -> new BookStoreException(BookStoreException.ExceptionTypes.USER_NOT_FOUND));

        List<CartDetails> cartDetailsList = cartRepository.findByUserDetailsModel(findTheExistedUser);


        List<CustomerDetails> customerDetailsList = customerDetailsRepository.findByUserDetails(findTheExistedUser);
        OderDetailsModel oderDetailsModel = new OderDetailsModel();
        oderDetailsModel.setCart(cartDetailsList.get(0));
        oderDetailsModel.setUser(findTheExistedUser);
        oderDetailsModel.setCustomer(customerDetailsList.get(0));
        //oderDetailsModel.getBookCartDetails();

        OderDetailsModel saveAnOrder = orderDetailsRepository.save(oderDetailsModel);
        System.out.println("the order placed successfully : "+saveAnOrder);


        return "hurray !!! your order of order id "+saveAnOrder.getId()+"is successfull";
    }

    @Override
    public List<OderDetailsModel> getAllOrders(String token) {
        UUID userId = jwtToken.decodeJWT(token);

        UserDetailsModel findTheExistedUser = userDetailsRepository.findById(userId).
                orElseThrow(() ->new BookStoreException(BookStoreException.ExceptionTypes.USER_NOT_FOUND));

        List<OderDetailsModel> oderDetailsModelByUser = orderDetailsRepository.findOderDetailsModelByUser(findTheExistedUser);
        return oderDetailsModelByUser;
    }
}

