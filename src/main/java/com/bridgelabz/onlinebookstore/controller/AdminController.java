package com.bridgelabz.onlinebookstore.controller;

import com.bridgelabz.onlinebookstore.dto.*;
import com.bridgelabz.onlinebookstore.model.AdminDetailsModel;
import com.bridgelabz.onlinebookstore.services.IAdminService;
import com.bridgelabz.onlinebookstore.utils.Token;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("/Admin")
@ComponentScan
@EnableAutoConfiguration
public class AdminController {

    @Autowired
    IAdminService adminService;

    Token jwtToken = new Token();

    @PostMapping("/login")
    public ResponseEntity<ResponseDto> adminLogin (HttpServletResponse httpServletResponse, @Valid @RequestBody AdminLoginDto adminLoginDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return new ResponseEntity(bindingResult.getAllErrors().get(0).getDefaultMessage(),HttpStatus.BAD_REQUEST);
        }
        AdminDetailsModel adminLogin= adminService.adminLogin(adminLoginDto);
        String token = jwtToken.generateAdminLoginToken(adminLogin);
        httpServletResponse.setHeader("Authorization",token);
        return new ResponseEntity (new ResponseDto("LOGIN SUCCESSFUL",
                "200",token,adminLogin.getFullName()),
                HttpStatus.OK);
    }

}
