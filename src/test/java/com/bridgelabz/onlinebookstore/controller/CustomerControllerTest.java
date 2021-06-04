package com.bridgelabz.onlinebookstore.controller;

import com.bridgelabz.onlinebookstore.dto.CustomerDetailsDto;
import com.bridgelabz.onlinebookstore.dto.ResponseDto;
import com.bridgelabz.onlinebookstore.services.ICustomerService;
import com.bridgelabz.onlinebookstore.utils.FileProperties;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class CustomerControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ICustomerService customerService;

    @MockBean
    FileProperties fileProperties;


   // HttpHeaders httpHeaders = new HttpHeaders();
   // Gson gson = new Gson();
    CustomerDetailsDto customerDetailsDto;


    @Test
    public void givenCustomerDetailsToAddInDatabase_WhenAdded_ThenShouldReturnCorrectMessage() throws Exception {
    customerDetailsDto.pinCode="411015";
    customerDetailsDto.locality="Kalas";
    customerDetailsDto.address="CME";
    customerDetailsDto.city="Pune";
    customerDetailsDto.landmark="Alandi";
    customerDetailsDto.addressType="Home";
    String toJson = new Gson().toJson(customerDetailsDto);
        System.out.println("the tojson is "+toJson);
    MvcResult mvcResult = this.mockMvc.perform(post("/customer/addDetail_customer")
            .content(toJson)
            .contentType(MediaType.APPLICATION_JSON)).andReturn();
        System.out.println(mvcResult.getResponse());
    String message="CUSTOMER DETAILS ADDED SUCCESFULLY : ";
        Assert.assertEquals(message,new Gson().fromJson(mvcResult.getResponse().getContentAsString(), ResponseDto.class).getMessage());
}
}
