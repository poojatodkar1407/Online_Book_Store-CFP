package com.bridgelabz.onlinebookstore.services;

import com.bridgelabz.onlinebookstore.dto.UserLoginDto;

public interface IUserService {
    String userLogin(UserLoginDto userLoginDto);
}
