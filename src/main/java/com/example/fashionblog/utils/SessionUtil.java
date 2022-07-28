package com.example.fashionblog.utils;

import com.example.fashionblog.exception.UserNotFound;
import com.example.fashionblog.model.User;
import com.example.fashionblog.repository.AuthenticationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class SessionUtil {

    private final HttpSession httpSession;

    private final AuthenticationRepository authenticationRepository;

    public Long getLoggedInUser(){
        Long userId = (Long) httpSession.getAttribute("user_id");
        if(userId == null) {
            throw new UserNotFound("You are not yet logged in!");
        }
        return userId;
    }

    public User findUserById(Long userId) {
        return authenticationRepository.findById(userId).orElseThrow(()-> new UserNotFound("User not found!"));
    }
}
