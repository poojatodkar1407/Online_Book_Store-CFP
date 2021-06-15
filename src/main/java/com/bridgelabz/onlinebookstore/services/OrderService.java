package com.bridgelabz.onlinebookstore.services;

import com.bridgelabz.onlinebookstore.exception.BookStoreException;
import com.bridgelabz.onlinebookstore.model.*;
import com.bridgelabz.onlinebookstore.repository.*;
import com.bridgelabz.onlinebookstore.utils.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        List<BookCartDetails> bookCartDetails =bookCartRepository.getCartItems(cartDetailsList.get(0).getBookId());

        List<CustomerDetails> customerDetailsList = customerDetailsRepository.findByUserDetails(findTheExistedUser);
        OderDetailsModel oderDetailsModel = new OderDetailsModel();
        oderDetailsModel.setCart(cartDetailsList.get(0));
        oderDetailsModel.setUser(findTheExistedUser);
        oderDetailsModel.setCustomer(customerDetailsList.get(0));
        oderDetailsModel.setBookCartDetails(bookCartDetails);
        oderDetailsModel.setOrderId(generateOrderId());
        oderDetailsModel.setTotalPrice(totalPrice);
        oderDetailsModel.setOrderPlacedDate(LocalDate.now());
        //oderDetailsModel.getBookCartDetails();

        OderDetailsModel saveAnOrder = orderDetailsRepository.save(oderDetailsModel);
        System.out.println("the order placed successfully : "+saveAnOrder);


        return "hurray !!! your order of order id "+saveAnOrder.getOrderId()+"is successfull";
    }

    private int generateOrderId(){
        boolean isUnique = false;
        Integer orderId = 0;

        while (!isUnique){
            orderId = (int) Math.floor(100000 + Math.random() * 999999);
            Optional<OderDetailsModel> detailsModel = orderDetailsRepository.findByOrderId(orderId);
            if( !detailsModel.isPresent())
                isUnique = true;

        }
        return orderId;

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

