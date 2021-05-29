package com.bridgelabz.onlinebookstore.services;

import com.bridgelabz.onlinebookstore.dto.UserLoginDto;
import com.bridgelabz.onlinebookstore.exception.UserException;
import com.bridgelabz.onlinebookstore.model.UserDetailsModel;
import com.bridgelabz.onlinebookstore.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    HttpServletRequest httpServletRequest;

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
}
