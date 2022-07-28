package com.example.fashionblog.service;

public interface LikedPostService {
    String likedPost(Long id);

    String unlikePost(Long id);
}
