package com.bridgelabz.onlinebookstore.services;

import com.bridgelabz.onlinebookstore.dto.AdminDetailsDto;
import com.bridgelabz.onlinebookstore.dto.AdminLoginDto;
import com.bridgelabz.onlinebookstore.dto.UserLoginDto;
import com.bridgelabz.onlinebookstore.model.AdminDetailsModel;

public interface IAdminService {
    AdminDetailsModel adminLogin(AdminLoginDto adminLoginDto);
}
