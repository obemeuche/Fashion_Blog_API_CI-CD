package com.example.fashionblog.service;

import com.example.fashionblog.dto.CommentDto;
import com.example.fashionblog.model.Comment;

public interface CommentService {
    Comment writeComment(Long id, CommentDto commentDto);

    Comment editComment(Long id, CommentDto commentDto);

    Comment viewCommentById(Long id);

    String deleteComment(Long id);
}
