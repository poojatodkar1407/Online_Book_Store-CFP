package com.bridgelabz.onlinebookstore.service;

import com.bridgelabz.onlinebookstore.dto.UserDetailsDto;
import com.bridgelabz.onlinebookstore.exception.BookStoreException;
import com.bridgelabz.onlinebookstore.model.UserDetailsModel;
import com.bridgelabz.onlinebookstore.repository.UserDetailsRepository;
import com.bridgelabz.onlinebookstore.utils.MailService;
import com.bridgelabz.onlinebookstore.utils.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements IUserService{

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    Token token= new Token();

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
        String tokenId = token.generateVerificationToken(userDetailsModel);

        String requestUrl ="http://localhost:8080/user/verify/email/"+tokenId;
        System.out.println("token from registration is "+tokenId);
        try {
            mailService.sendMail(requestUrl,"the verification link is ",userDetailsModel.getEmailID());
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return new UserDetailsModel(saveDetails);
    }
    @Override
    public void verifyEmail(String tokenId) {
        System.out.println("your token id is "+tokenId);
        UUID tokenjwt = token.decodeJWT(tokenId);
        System.out.println(tokenjwt);


        Optional<UserDetailsModel> userId = userDetailsRepository.findById(tokenjwt);

        System.out.println("userid" +userId);

        if(!userId.isPresent()) {
            throw  new BookStoreException(BookStoreException.ExceptionTypes.USER_NOT_FOUND);
        }

        userId.get().setVerified(true);
        userDetailsRepository.save(userId.get());

    }


}
