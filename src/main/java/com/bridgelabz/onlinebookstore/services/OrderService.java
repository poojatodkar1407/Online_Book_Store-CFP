package com.bridgelabz.onlinebookstore.services;

import com.bridgelabz.onlinebookstore.exception.BookStoreException;
import com.bridgelabz.onlinebookstore.model.*;
import com.bridgelabz.onlinebookstore.repository.*;
import com.bridgelabz.onlinebookstore.utils.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Autowired
    BookRepository bookRepository;

    @Override
    public String placeAnOrder(Double totalPrice, String token) {
        UUID userId = jwtToken.decodeJWT(token);
        CartDetails cartDetails =getCart(userId);
        List<BookCartDetails> cartBooks = bookCartRepository.findByCartDetailsCartIdAndOrderStatusIsFalse(cartDetails.getCartId());
        CustomerDetails customerDetails=customerDetailsRepository.findByUserDetails(cartDetails.getUserDetailsModel()).get(0);
        Integer orderId = generateOrderId();
        OderDetailsModel oderDetailsModel =new OderDetailsModel(orderId,totalPrice,LocalDate.now(),cartDetails,cartDetails.getUserDetailsModel(),
                                                                customerDetails,cartBooks);
        OderDetailsModel saveOrder = orderDetailsRepository.save(oderDetailsModel);
        cartBooks.forEach(cartBook -> {
            cartBook.setOrderDetails(oderDetailsModel);
            cartBook.setOrderStatus(true);

            bookRepository.updateStock(cartBook.getQuantity(),cartBook.getBookDetailsModel().bookId);
        });

        cartBooks.forEach(cartBook -> {
            BookDetailsModel searchBook = bookRepository.
                    findById(cartBook.getBookDetailsModel().bookId).
                    orElseThrow(() -> new BookStoreException(BookStoreException.ExceptionTypes.BOOK_NOT_FOUND));

            int bookQuantity = cartBook.getQuantity();
            int quantityCartOfBook = searchBook.getQuantity();
            searchBook.setQuantity(quantityCartOfBook-bookQuantity);
            searchBook.setAdded(false);
            bookRepository.save(searchBook);

        });
        bookCartRepository.updateOrderPlacedStatus(cartDetails.getCartId());
        return "hurray !!! your order of order id "+saveOrder.getOrderId()+ "is successfull";
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
    public List<BookCartDetails> getAllOrders(String token) {
        UUID userId = jwtToken.decodeJWT(token);

        UserDetailsModel findTheExistedUser = userDetailsRepository.findById(userId).
                orElseThrow(() ->new BookStoreException(BookStoreException.ExceptionTypes.USER_NOT_FOUND));

        List<OderDetailsModel> oderDetailsModelByUser = orderDetailsRepository.findOderDetailsModelByUser(findTheExistedUser);
        List detailsList = new ArrayList<>();
        List<BookCartDetails> detailsListOfOrder = new ArrayList<>();

        for (OderDetailsModel order : oderDetailsModelByUser){
            OderDetailsModel orderDetails = orderDetailsRepository.findByOrderId(order.orderId).get();

            detailsListOfOrder=bookCartRepository.findBookCartDetailsByOrderDetails(orderDetails);
            List<BookCartSummary> collectBookCartDetails = detailsListOfOrder.stream()
                    .map(summary -> new BookCartSummary(summary)).collect(Collectors.toList());
            detailsList.add(collectBookCartDetails);


        }
           System.out.println(detailsList);


        return detailsList;
    }

    private CartDetails getCart(UUID userId) {
        UserDetailsModel findTheExistedUser = userDetailsRepository.findById(userId).
                orElseThrow(() ->new BookStoreException(BookStoreException.ExceptionTypes.USER_NOT_FOUND));

        CartDetails cartDetails= cartRepository.findByUserDetailsModel(findTheExistedUser).
                orElseThrow(() -> new BookStoreException(BookStoreException.ExceptionTypes.CART_NOT_PRESENT));

        System.out.println("cart details are "+cartDetails);
        return cartDetails;

    }
}

