package com.bridgelabz.onlinebookstore.services;

import com.bridgelabz.onlinebookstore.dto.AdminLoginDto;
import com.bridgelabz.onlinebookstore.exception.AdminException;
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
    public AdminDetailsModel adminLogin(AdminLoginDto adminLoginDto) {
        Optional<AdminDetailsModel> adminDetailsByEmail = adminDetailsRepository.findByEmailID(adminLoginDto.getEmailID());
        if (!adminDetailsByEmail.isPresent()) {
            throw new AdminException(AdminException.ExceptionType.INVALID_DATA);
        }
        if(adminDetailsByEmail.get().isVerified){
            boolean password = bCryptPasswordEncoder.matches(adminLoginDto.password, adminDetailsByEmail.get().password);
            if (!password) {
                throw new AdminException(AdminException.ExceptionType.PASSWORD_INVALID);
            }
            String token = jwtToken.generateAdminLoginToken(adminDetailsByEmail.get());
            return adminDetailsByEmail.get();
        }
        throw new AdminException(AdminException.ExceptionType.EMAIL_NOT_FOUND);
    }


}
