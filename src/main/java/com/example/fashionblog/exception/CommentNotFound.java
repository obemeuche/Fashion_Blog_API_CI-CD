package com.example.fashionblog.exception;

public class CommentNotFound extends RuntimeException{
    public CommentNotFound(String message) {
        super(message);
    }
}
