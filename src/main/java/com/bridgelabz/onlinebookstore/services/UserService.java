package com.bridgelabz.onlinebookstore.services;

import com.bridgelabz.onlinebookstore.dto.UserDetailsDto;
import com.bridgelabz.onlinebookstore.dto.UserLoginDto;
import com.bridgelabz.onlinebookstore.exception.BookStoreException;
import com.bridgelabz.onlinebookstore.exception.UserException;
import com.bridgelabz.onlinebookstore.model.UserDetailsModel;
import com.bridgelabz.onlinebookstore.repository.UserDetailsRepository;
import com.bridgelabz.onlinebookstore.utils.MailService;
import com.bridgelabz.onlinebookstore.utils.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    Token jwtToken = new Token();

    @Autowired
    MailService mailService;

    @Override
    public UserDetailsModel addUser(UserDetailsDto userDetails) {
        Optional<UserDetailsModel> byEmailId = userDetailsRepository.findByEmailID(userDetails.getEmailID());
        if(byEmailId.isPresent()){
            throw new BookStoreException(BookStoreException.ExceptionTypes.USER_ALREADY_PRESENT);
        }
        String password = bCryptPasswordEncoder.encode(userDetails.getPassword());
        UserDetailsModel userDetailsModel = new UserDetailsModel(userDetails.getFullName(),
                                                                 userDetails.getPhoneNumber(),
                                                                 userDetails.getEmailID(),
                                                                  password);
        UserDetailsModel saveDetails = userDetailsRepository.save(userDetailsModel);
        String tokenId = jwtToken.generateVerificationToken(userDetailsModel);
        String requestUrl ="http://localhost:3000/verify/"+tokenId;
        System.out.println("token from registration is "+tokenId);
        try {
            mailService.sendMail(requestUrl,"the verification link is ",userDetailsModel.getEmailID());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return saveDetails;
    }


    @Override
    public void verifyEmail(String tokenId) {
        System.out.println("your token id is "+tokenId);
        UUID tokenjwt = jwtToken.decodeJWT(tokenId);
        System.out.println(tokenjwt);
        Optional<UserDetailsModel> userId = userDetailsRepository.findById(tokenjwt);
        System.out.println("userid" +userId);
        if(!userId.isPresent()) {
            throw  new BookStoreException(BookStoreException.ExceptionTypes.USER_NOT_FOUND);
        }
        userId.get().isVerified=true;
        userId.get().updatedAt= LocalDateTime.now();
        userDetailsRepository.save(userId.get());

    }


    @Override
     public String userLogin(UserLoginDto userLoginDto) {
        System.out.println(userLoginDto.emailID);
        Optional<UserDetailsModel> userDetailsByEmail = userDetailsRepository.findByEmailID(userLoginDto.getEmailID());
        System.out.println("the optional message is "+userDetailsByEmail);

        if (!userDetailsByEmail.isPresent()) {
            throw new UserException(UserException.ExceptionType.EMAIL_NOT_FOUND);
        }
        if(userDetailsByEmail.get().isVerified){
            boolean password = bCryptPasswordEncoder.matches(userLoginDto.password, userDetailsByEmail.get().password);
            if (!password) {
                throw new UserException(UserException.ExceptionType.PASSWORD_INVALID);
            }
            String tokenString = jwtToken.generateLoginToken(userDetailsByEmail.get());
            return tokenString;
        }
        throw new UserException(UserException.ExceptionType.EMAIL_NOT_FOUND);
    }



    @Override
    public String resetPasswordLink(String email) throws MessagingException {
        UserDetailsModel user = userDetailsRepository.findByEmailID(email).orElseThrow(() -> new UserException(UserException.ExceptionType.EMAIL_NOT_FOUND));
        String tokenGenerate = jwtToken.generateVerificationToken(user);
        String urlToken = "Click on below link to Reset your Password \n"
                + "http://localhost:3000/reset/" + tokenGenerate;
        mailService.sendMail(urlToken, "Reset Password", user.emailID);
        return "Reset Password Link Has Been Sent To Your Email Address";
    }


    @Override
    public String resetPassword(String password, String urlToken) {
        System.out.println(urlToken);
        UUID userId = jwtToken.decodeJWT(urlToken);
        System.out.println(userId);
        UserDetailsModel userDetails = userDetailsRepository.findById(userId).orElseThrow(() -> new UserException(UserException.ExceptionType.INVALID_DATA));
        String encodePassword = bCryptPasswordEncoder.encode(password);
        userDetails.password = encodePassword;
        userDetailsRepository.save(userDetails);
        return "Password Has Been Reset";

    }
}