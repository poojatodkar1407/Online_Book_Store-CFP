package com.bridgelabz.onlinebookstore.services;

import com.bridgelabz.onlinebookstore.model.OderDetailsModel;
import com.bridgelabz.onlinebookstore.repository.OrderDetailsRepository;

import java.util.List;

public interface IOrderService {

    String placeAnOrder(Double totalPrice, String token);

    List<OderDetailsModel> getAllOrders(String token);
}
