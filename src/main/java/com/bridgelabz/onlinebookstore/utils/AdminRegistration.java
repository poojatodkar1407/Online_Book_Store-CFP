package com.bridgelabz.onlinebookstore.utils;

import com.bridgelabz.onlinebookstore.dto.AdminDetailsDto;
import com.bridgelabz.onlinebookstore.dto.UserDetailsDto;
import com.bridgelabz.onlinebookstore.enums.UserRole;
import com.bridgelabz.onlinebookstore.model.AdminDetailsModel;
import com.bridgelabz.onlinebookstore.model.UserDetailsModel;
import com.bridgelabz.onlinebookstore.repository.AdminDetailsRepository;
import com.bridgelabz.onlinebookstore.repository.UserDetailsRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AdminRegistration {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    AdminDetailsRepository adminDetailsRepository;

    @EventListener
    public void appReady(ApplicationReadyEvent event){
        Optional<AdminDetailsModel> admin = adminDetailsRepository.findByEmailID("yennefer9713@gmail.com");
        if(!admin.isPresent()){
            AdminDetailsDto adminDetailsDto= new AdminDetailsDto("Admin","919665592026","yennefer9713@gmail.com","Skyispink@98",true,UserRole.ADMIN);
            AdminDetailsModel adminDetailsModel = new AdminDetailsModel(adminDetailsDto);
            adminDetailsModel.setPassword(bCryptPasswordEncoder.encode(adminDetailsDto.password));
            adminDetailsRepository.save(adminDetailsModel);
        }

    }

}
