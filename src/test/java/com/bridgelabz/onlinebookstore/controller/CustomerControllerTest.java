//package com.bridgelabz.onlinebookstore.controller;
//
//import com.bridgelabz.onlinebookstore.dto.*;
//import com.bridgelabz.onlinebookstore.exception.BookStoreException;
//import com.bridgelabz.onlinebookstore.model.CustomerDetails;
//import com.bridgelabz.onlinebookstore.services.ICustomerService;
//import com.bridgelabz.onlinebookstore.utils.FileProperties;
//import com.google.gson.Gson;
//import org.junit.Assert;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.http.HttpHeaders;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class CustomerControllerTest {
//    @Autowired
//    MockMvc mockMvc;
//
//    @MockBean
//    ICustomerService customerService;
//
//    @MockBean
//    FileProperties fileProperties;
//
//
//
//   HttpHeaders httpHeaders = new HttpHeaders();
//    Gson gson = new Gson();
//    CustomerDetailsDto customerDetailsDto;
//
//
//
//    @Test
//    public void givenCustomerDetailsToAddInDatabase_WhenAdded_ShouldReturnCorrectMessage() throws Exception {
//        httpHeaders.set("token","eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIzZGM4YTQ4MS02NGIyLTQzMDQtODBhMi00M2I0ZmU5YzAwZjIiLCJzdWIiOiJTaGFtYWwgUGF0aWwiLCJpYXQiOjE2MjI4ODQ4NDMsImV4cCI6MTYyMjk4NDg0M30.xxxUkzp36rEJZfaD2rzM5jdsRH0Vs4eZA0OATZlN7yQ");
//        customerDetailsDto = new CustomerDetailsDto("411015","Kalas","CME","Pune","Kalas","Home");
//        String toJson = gson.toJson(customerDetailsDto);
//        String message = "CUSTOMER DETAILS ADDED SUCCESFULLY : ";
//        MvcResult mvcResult = this.mockMvc.perform(post("/customer/addDetail_customer")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(toJson)
//                .headers(httpHeaders)).andReturn();
//        Assert.assertTrue(mvcResult.getResponse().getContentAsString().contains("CUSTOMER DETAILS ADDED SUCCESFULLY : "));
//    }
//
//    @Test
//    void givenCustomerDetailsToAddInDatabase_WhenUserFound_ShouldThrowException() throws Exception {
//        try {
//            when(customerService.addCustomerDetails(any(), any())).thenThrow(new BookStoreException("user not found"));
//            this.mockMvc.perform(post("/customer/addDetail_customer")).andReturn();
//        } catch (BookStoreException e) {
//            Assert.assertSame("user not found", e.getMessage());
//        }
//    }
//
//    @Test
//    public void givenRequestToFetchCustomerDetailsFromDatabase_ShouldReturnCorrectData() throws Exception {
//        httpHeaders.set("token","eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIzZGM4YTQ4MS02NGIyLTQzMDQtODBhMi00M2I0ZmU5YzAwZjIiLCJzdWIiOiJTaGFtYWwgUGF0aWwiLCJpYXQiOjE2MjI4ODQ4NDMsImV4cCI6MTYyMjk4NDg0M30.xxxUkzp36rEJZfaD2rzM5jdsRH0Vs4eZA0OATZlN7yQ");
//        customerDetailsDto = new CustomerDetailsDto("411015","Kalas","CME","Pune","Kalas","Home");
//        CustomerDetails customerDetails = new CustomerDetails(customerDetailsDto);
//        List<CustomerDetails> customerDetailsList = new ArrayList<>();
//        customerDetailsList.add(customerDetails);
//        String toJson = gson.toJson(customerDetails);
//        this.mockMvc.perform(get("/customer/getall_details")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(toJson)
//                .headers(httpHeaders)
//                .characterEncoding("utf-8"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void givenCustomerDetailsToAddInDatabase_WhenPincodeNotProper_ShouldReturnProperMessage() throws Exception {
//        httpHeaders.set("token","eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIzZGM4YTQ4MS02NGIyLTQzMDQtODBhMi00M2I0ZmU5YzAwZjIiLCJzdWIiOiJTaGFtYWwgUGF0aWwiLCJpYXQiOjE2MjI4ODQ4NDMsImV4cCI6MTYyMjk4NDg0M30.xxxUkzp36rEJZfaD2rzM5jdsRH0Vs4eZA0OATZlN7yQ");
//        customerDetailsDto = new CustomerDetailsDto("411015","Kalas","CME","Pune","Kalas","Home");
//        String toJson = gson.toJson(customerDetailsDto);
//        String message = "Please enter a valid zip code";
//        MvcResult mvcResult = this.mockMvc.perform(post("/customer/addDetail_customer")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(toJson)
//                .headers(httpHeaders)).andReturn();
//        String response = mvcResult.getResponse().getContentAsString();
//        ResponseDto responseDto = gson.fromJson(response, ResponseDto.class);
//        String responseMessage = responseDto.message;
//        Assert.assertNotEquals(message, responseMessage);
//    }
//
//
//}
//
//
