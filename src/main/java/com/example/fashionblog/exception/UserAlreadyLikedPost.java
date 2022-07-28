package com.example.fashionblog.exception;

public class UserAlreadyLikedPost extends RuntimeException{
    public UserAlreadyLikedPost(String message) {
        super(message);
    }
}
