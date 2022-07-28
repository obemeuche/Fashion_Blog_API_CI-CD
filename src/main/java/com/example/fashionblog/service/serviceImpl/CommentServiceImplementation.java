package com.example.fashionblog.service.serviceImpl;

import com.example.fashionblog.dto.CommentDto;
import com.example.fashionblog.enums.Role;
import com.example.fashionblog.exception.*;
import com.example.fashionblog.model.Comment;
import com.example.fashionblog.model.Post;
import com.example.fashionblog.model.User;
import com.example.fashionblog.repository.CommentRepository;
import com.example.fashionblog.repository.PostRepository;
import com.example.fashionblog.service.CommentService;
import com.example.fashionblog.utils.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommentServiceImplementation implements CommentService {

    private final SessionUtil util;

    private final PostRepository postRepository;

    private final CommentRepository commentRepository;

    @Override
    public Comment writeComment(Long id, CommentDto commentDto) {
        Long userId = util.getLoggedInUser();
        User user = util.findUserById(userId);

        if (user == null){
            throw new UserNotFound("You are not logged in");
        }

        if (user.getRole().equals(Role.ADMIN)){
            throw new PermissionDenied("You are an admin!");
        }

        Post adminPost = postRepository.findById(id)
                .orElseThrow(()-> new PostNotFound("Post is not Found!"));

        Comment comment = Comment.builder()
                .content(commentDto.getContent())
                .user(user)
                .post(adminPost)
                .build();

        commentRepository.save(comment);
        adminPost.getComments().add(comment);
        postRepository.save(adminPost);
        return comment;
    }

    @Override
    public Comment viewCommentById(Long id) {

        Comment comment = commentRepository.findById(id)
                .orElseThrow(()-> new CategoryNotFound("Comment not found!"));

        return comment;
    }

    @Override
    public Comment editComment(Long id, CommentDto commentDto) {
        Long userId = util.getLoggedInUser();
        User user = util.findUserById(userId);

        if (user == null){
            throw new UserNotFound("You are not logged in");
        }

        if (user.getRole().equals(Role.ADMIN)){
            throw new PermissionDenied("You are an admin!");
        }

        Comment comment = commentRepository.findByIdAndUserId(id, userId)
                .orElseThrow(()-> new CommentNotFound("Comment not found!"));

        if (Objects.nonNull(commentDto.getContent()) && !commentDto.getContent().equals("")){
            comment.setContent(commentDto.getContent());
        }

        return commentRepository.save(comment);
    }


    @Override
    public String deleteComment(Long commentId) {
        Long userId = util.getLoggedInUser();
        User user = util.findUserById(userId);

        if (user == null){
            throw new UserNotFound("You are not logged in");
        }

        Comment comment = commentRepository.findByIdAndUserId(commentId, userId)
                .orElseThrow(()-> new CategoryNotFound("Comment not found!"));

        commentRepository.delete(comment);

        return "Comment deleted successfully!";
    }
}
