package com.example.fashionblog.service;

import com.example.fashionblog.dto.LoginDto;
import com.example.fashionblog.dto.SignupDto;

public interface AuthenticationService {

    String signup(SignupDto signupDto);

    String login(LoginDto loginDto);

    String logout();

}
