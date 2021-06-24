package com.bridgelabz.onlinebookstore.services;

import com.bridgelabz.onlinebookstore.dto.UserDetailsDto;
import com.bridgelabz.onlinebookstore.dto.UserLoginDto;
import com.bridgelabz.onlinebookstore.model.UserDetailsModel;

import javax.mail.MessagingException;
import java.util.List;
import java.util.UUID;

public interface IUserService {
    UserDetailsModel addUser(UserDetailsDto userDetails);

    void verifyEmail(String tokenId);

    UserDetailsModel userLogin(UserLoginDto userLoginDto);

    String resetPasswordLink(String email) throws MessagingException;

    String resetPassword(String password, String urlToken);

    List<UserDetailsModel> getUserInformation(String token);
}
