package com.bridgelabz.onlinebookstore.services;

import com.bridgelabz.onlinebookstore.dto.AdminDetailsDto;
import com.bridgelabz.onlinebookstore.dto.AdminLoginDto;
import com.bridgelabz.onlinebookstore.dto.UserLoginDto;

public interface IAdminService {
    String adminLogin(AdminLoginDto adminLoginDto);
}
