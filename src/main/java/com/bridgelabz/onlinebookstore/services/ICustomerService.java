package com.bridgelabz.onlinebookstore.services;

import com.bridgelabz.onlinebookstore.dto.CustomerDetailsDto;
import com.bridgelabz.onlinebookstore.model.CustomerDetails;

import java.util.List;

public interface ICustomerService {
    CustomerDetails addCustomerDetails(CustomerDetailsDto customerDetailsDto, String token);

   List<CustomerDetails> getAllCustumerDetails(String token);
}
