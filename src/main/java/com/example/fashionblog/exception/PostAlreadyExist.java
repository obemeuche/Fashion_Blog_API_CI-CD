package com.example.fashionblog.exception;

public class PostAlreadyExist extends RuntimeException{
    public PostAlreadyExist(String message) {
        super(message);
    }
}
