package com.example.fashionblog.service;

import com.example.fashionblog.dto.PostDto;
import com.example.fashionblog.model.Category;
import com.example.fashionblog.model.Post;

import java.util.List;

public interface PostService {
    String createPost(Long id, PostDto postDto);

    Post viewPostById(Long id);

    String updatePost(Long id, PostDto postDto);

    String deletePost(Long id);
}
