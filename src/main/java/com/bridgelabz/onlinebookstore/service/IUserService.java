package com.bridgelabz.onlinebookstore.service;

import com.bridgelabz.onlinebookstore.dto.UserDetailsDto;
import com.bridgelabz.onlinebookstore.model.UserDetailsModel;


public interface IUserService {

    UserDetailsModel addUser(UserDetailsDto userDetails);

    void verifyEmail(String tokenId);
}
