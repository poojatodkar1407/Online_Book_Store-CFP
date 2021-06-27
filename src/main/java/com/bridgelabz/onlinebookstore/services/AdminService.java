package com.bridgelabz.onlinebookstore.services;

import com.bridgelabz.onlinebookstore.dto.AdminLoginDto;
import com.bridgelabz.onlinebookstore.exception.UserException;
import com.bridgelabz.onlinebookstore.model.AdminDetailsModel;
import com.bridgelabz.onlinebookstore.repository.AdminDetailsRepository;
import com.bridgelabz.onlinebookstore.utils.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService implements IAdminService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AdminDetailsRepository adminDetailsRepository;

    Token jwtToken = new Token();

    @Override
    public String adminLogin(AdminLoginDto adminLoginDto) {
        Optional<AdminDetailsModel> adminDetailsByEmail = adminDetailsRepository.findByEmailID(adminLoginDto.getEmailID());
        if (!adminDetailsByEmail.isPresent()) {
            throw new UserException(UserException.ExceptionType.EMAIL_NOT_FOUND);
        }
        if(adminDetailsByEmail.get().isVerified){
            boolean password = bCryptPasswordEncoder.matches(adminLoginDto.password, adminDetailsByEmail.get().password);
            if (!password) {
                throw new UserException(UserException.ExceptionType.PASSWORD_INVALID);
            }
            String token = jwtToken.generateAdminLoginToken(adminDetailsByEmail.get());
            return token;
        }

        throw new UserException(UserException.ExceptionType.EMAIL_NOT_FOUND);
    }
}
