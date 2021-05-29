package com.bridgelabz.onlinebookstore.services;

import com.bridgelabz.onlinebookstore.dto.UserLoginDto;

import javax.mail.MessagingException;

public interface IUserService {
    String userLogin(UserLoginDto userLoginDto);
    String resetPasswordLink(String email, String urlToken) throws MessagingException;
    String resetPassword(String password, String urlToken);
}
