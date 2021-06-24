package com.bridgelabz.onlinebookstore.controller;

import com.bridgelabz.onlinebookstore.exception.BookStoreException;
import com.bridgelabz.onlinebookstore.model.CustomerDetails;
import com.bridgelabz.onlinebookstore.model.OderDetailsModel;
import com.bridgelabz.onlinebookstore.services.IOrderService;
import com.bridgelabz.onlinebookstore.utils.FileProperties;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IOrderService orderService;

    @MockBean
    FileProperties fileProperties;


    Gson gson = new Gson();
    HttpHeaders httpHeaders = new HttpHeaders();

//    @Test
//    void givenRequest_WhenOrdersIsNotEmpty_ShouldReturnOrdersRecords() throws Exception {
//        List<OderDetailsModel> orderList = new ArrayList<>();
//        OderDetailsModel oderDetailsModel = new OderDetailsModel();
//        oderDetailsModel.setTotalPrice(500.00);
//        oderDetailsModel.setOrderId(989814);
//        oderDetailsModel.setCustomer(new CustomerDetails());
//        orderList.add(oderDetailsModel);
//        when(orderService.getAllOrders(any())).thenReturn(orderList);
//        MvcResult mvcResult = this.mockMvc.perform(get("/order/getall_order_details")).andReturn();
//        String response = mvcResult.getResponse().getContentAsString();
//        Assert.assertTrue(response.contains("989814"));
//    }

    @Test
    void givenOrderDetails_WhenOrderPlaced_ShouldReturnMessage() throws Exception {
        httpHeaders.set("token", "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2YjFhY2ZlMy0xNDkxLTQ5N2UtOTUyNi1hZDVlYTA4NjU1ZmYiLCJzdWIiOiJZZW5uZXIiLCJpYXQiOjE2MjI4Mjc0MTMsImV4cCI6MTYyMjkyNzQxM30.TleAMgju7kHBU2jdKA6jm_bHkO3eGt-vq3rTgZwudns");
        String message = "Order Placed succesFully : ";
        String totalPrice = "500.00";
        when(orderService.placeAnOrder(anyDouble(),any())).thenReturn(String.valueOf(552255));
        MvcResult mvcResult = this.mockMvc.perform(post("/order/addorder")
                .param("totalPrice", totalPrice)
                .headers(httpHeaders).characterEncoding("utf-8"))
                .andReturn();
    }


    @Test
    void givenOrderDetails_WhenUserFound_ShouldThrowException() throws Exception {
        try {
            when(orderService.placeAnOrder(any(), any())).thenThrow(new BookStoreException("user not found"));
            this.mockMvc.perform(post("/customer/addorder/")).andReturn();
        } catch (BookStoreException e) {
            Assert.assertSame("user not found", e.getMessage());
        }
    }
}