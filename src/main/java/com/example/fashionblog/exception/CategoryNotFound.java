package com.example.fashionblog.exception;

public class CategoryNotFound extends RuntimeException  {
    public CategoryNotFound(String message) {
        super(message);
    }
}