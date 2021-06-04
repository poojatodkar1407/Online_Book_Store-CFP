package com.bridgelabz.onlinebookstore.services;

import com.bridgelabz.onlinebookstore.dto.CustomerDetailsDto;
import com.bridgelabz.onlinebookstore.exception.BookStoreException;
import com.bridgelabz.onlinebookstore.model.CustomerDetails;
import com.bridgelabz.onlinebookstore.model.UserDetailsModel;
import com.bridgelabz.onlinebookstore.repository.CustomerDetailsRepository;
import com.bridgelabz.onlinebookstore.repository.UserDetailsRepository;
import com.bridgelabz.onlinebookstore.utils.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomerService implements ICustomerService{
    @Autowired
    Token jwtToken;

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    CustomerDetailsRepository customerDetailsRepository;

    @Override
    public CustomerDetails addCustomerDetails(CustomerDetailsDto customerDetailsDto, String token) {
        UUID userId = jwtToken.decodeJWT(token);

        UserDetailsModel findTheExistedUser = userDetailsRepository.findById(userId).
                orElseThrow(() ->new BookStoreException(BookStoreException.ExceptionTypes.USER_NOT_FOUND));

        List<CustomerDetails> listOfCustomerDetails = new ArrayList<>();
        CustomerDetails customerDetails = new CustomerDetails(customerDetailsDto.pinCode,
                                                              customerDetailsDto.locality,
                                                              customerDetailsDto.address,
                                                              customerDetailsDto.city,
                                                              customerDetailsDto.landmark,
                                                              customerDetailsDto.addressType);
        customerDetails.setUserDetails(findTheExistedUser);

        listOfCustomerDetails.add(customerDetails);
        CustomerDetails save = customerDetailsRepository.save(customerDetails);


        return save;
    }

    @Override
    public List<CustomerDetails> getAllCustumerDetails(String token) {
        UUID userId = jwtToken.decodeJWT(token);

        UserDetailsModel findTheExistedUser = userDetailsRepository.findById(userId).
                orElseThrow(() ->new BookStoreException(BookStoreException.ExceptionTypes.USER_NOT_FOUND));
        List<CustomerDetails> customerDetailsbyUserDetails = customerDetailsRepository.findByUserDetails(findTheExistedUser);
        return customerDetailsbyUserDetails;

    }
}
