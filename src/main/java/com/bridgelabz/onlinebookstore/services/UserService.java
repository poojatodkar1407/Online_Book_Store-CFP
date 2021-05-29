package com.bridgelabz.onlinebookstore.services;

import com.bridgelabz.onlinebookstore.dto.UserLoginDto;
import com.bridgelabz.onlinebookstore.exception.UserException;
import com.bridgelabz.onlinebookstore.model.UserDetailsModel;
import com.bridgelabz.onlinebookstore.repository.UserDetailsRepository;
import com.bridgelabz.onlinebookstore.utils.MailService;
import com.bridgelabz.onlinebookstore.utils.Token;
import org.aspectj.weaver.patterns.IToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    HttpServletRequest httpServletRequest;


    Token jwtToken;

    MailService mailService;

    @Override
    public String userLogin(UserLoginDto userLoginDto) {
        Optional<UserDetailsModel> userDetailsByEmail = userDetailsRepository.findByEmailID(userLoginDto.emailID);
        if (userDetailsByEmail.isPresent()) {
            if(userDetailsByEmail.get().isVerified){
                boolean password = bCryptPasswordEncoder.matches(userLoginDto.password, userDetailsByEmail.get().password);
                if (password) {
                    String tokenString = jwtToken.generateLoginToken(userDetailsByEmail.get());
                    return tokenString;
                }
                throw new UserException("Invalid Password!!!Please Enter Correct Password",UserException.ExceptionType.PASSWORD_INVALID);
            }
            throw new UserException("Please verify your email before proceeding", UserException.ExceptionType.EMAIL_NOT_FOUND);
        }
        throw new UserException("Enter Registered Email", UserException.ExceptionType.EMAIL_NOT_FOUND);
    }

    @Override
    public String resetPasswordLink(String email) throws MessagingException {
        UserDetailsModel user = userDetailsRepository.findByEmailID(email).orElseThrow(() -> new UserException("Email Not Found", UserException.ExceptionType.EMAIL_NOT_FOUND));
        String tokenGenerate = jwtToken.generateVerificationToken(user);
        String urlToken = "Click on below link to Reset your Password \n"
                + "http://localhost:8080/user/reset/Password/" + tokenGenerate;
        mailService.sendMail(urlToken, "Reset Password", user.emailID);
        return "Reset Password Link Has Been Sent To Your Email Address";
    }
    @Override
    public String resetPassword(String password, String urlToken) {
        UUID userId = jwtToken.decodeJWT(urlToken);
        UserDetailsModel userDetails = userDetailsRepository.findById(userId).orElseThrow(() -> new UserException("User Not Found", UserException.ExceptionType.INVALID_DATA));
        String encodePassword = bCryptPasswordEncoder.encode(password);
        userDetails.password = encodePassword;
        userDetailsRepository.save(userDetails);
        return "Password Has Been Reset";

    }
}