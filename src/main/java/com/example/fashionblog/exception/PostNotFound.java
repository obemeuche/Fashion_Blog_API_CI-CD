package com.example.fashionblog.exception;

public class PostNotFound extends RuntimeException{
    public PostNotFound(String message) {
        super(message);
    }
}
