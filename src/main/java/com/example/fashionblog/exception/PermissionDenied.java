package com.example.fashionblog.exception;

public class PermissionDenied extends RuntimeException{
    public PermissionDenied(String message) {
        super(message);
    }
}
