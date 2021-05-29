package com.bridgelabz.onlinebookstore.services;

import com.bridgelabz.onlinebookstore.dto.UserDetailsDto;
import com.bridgelabz.onlinebookstore.dto.UserLoginDto;
import com.bridgelabz.onlinebookstore.model.UserDetailsModel;

import javax.mail.MessagingException;

public interface IUserService {
    UserDetailsModel addUser(UserDetailsDto userDetails);
    void verifyEmail(String tokenId);
    String userLogin(UserLoginDto userLoginDto);
    String resetPasswordLink(String email) throws MessagingException;
    String resetPassword(String password, String urlToken);
}
