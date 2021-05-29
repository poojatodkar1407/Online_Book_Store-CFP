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
        Optional<UserDetailsModel> userDetailsByEmail = userDetailsRepository.findByEmail(userLoginDto.emailID);
        if (userDetailsByEmail.isPresent() && userDetailsByEmail.get().status) {
            boolean password = bCryptPasswordEncoder.matches(userLoginDto.password, userDetailsByEmail.get().password);
            if (!password) {
                throw new UserException("Invalid Password!!!Please Enter Correct Password", UserException.ExceptionType.PASSWORD_INVALID);
            }
            return "Login Successful";
        }
        throw new UserException("Enter Registered Email", UserException.ExceptionType.EMAIL_NOT_FOUND);
    }

    @Override
    public String resetPasswordLink(String email, String urlToken) throws MessagingException {
        UserDetailsModel user = userDetailsRepository.findByEmail(email).orElseThrow(() -> new UserException("Email Not Found", UserException.ExceptionType.EMAIL_NOT_FOUND));
        String tokenGenerate = jwtToken.generateVerificationToken(user);
        urlToken = "Click on below link to Reset your Password \n"
                + "http://localhost:8081/swagger-ui.html#!/user-controller/resetPasswordUsingPOST" + "\n token:" + tokenGenerate;
        mailService.sendMail(urlToken, "Reset Password", user.emailID);
        return "Reset Password Link Has Been Sent To Your Email Address";
    }

}