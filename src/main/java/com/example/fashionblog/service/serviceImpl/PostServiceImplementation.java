package com.example.fashionblog.service.serviceImpl;

import com.example.fashionblog.dto.PostDto;
import com.example.fashionblog.enums.Role;
import com.example.fashionblog.exception.CategoryNotFound;
import com.example.fashionblog.exception.PermissionDenied;
import com.example.fashionblog.exception.PostAlreadyExist;
import com.example.fashionblog.exception.PostNotFound;
import com.example.fashionblog.model.Category;
import com.example.fashionblog.model.Post;
import com.example.fashionblog.model.User;
import com.example.fashionblog.repository.CategoryRepository;
import com.example.fashionblog.repository.PostRepository;
import com.example.fashionblog.service.PostService;
import com.example.fashionblog.utils.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImplementation implements PostService {

    private final PostRepository postRepository;

    private final CategoryRepository categoryRepository;

    private final SessionUtil util;

    @Override
    public String createPost(Long id, PostDto postDto) {
        Long userId = util.getLoggedInUser();
        User user = util.findUserById(userId);

        if (user.getRole().equals(Role.CUSTOMER)){
            throw new PermissionDenied("Permission denied!");
        }

        String title = postDto.getTitle();
        Optional<Post> post = postRepository.findByTitle(title);

        if (post.isPresent()) {
            throw new PostAlreadyExist("Post with title exist!");
        }

        Category category = categoryRepository.findById(id).orElseThrow(()-> new CategoryNotFound("Not found!"));

        Post newPost = Post.builder()
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .imgUrl(postDto.getImgUrl())
                .likedPost(new ArrayList<>())
                .user(user)
                .comments(new ArrayList<>())
                .category(category)
                .build();

        postRepository.save(newPost);
        category.getPost().add(newPost);
        categoryRepository.save(category);
        return "New Post Created Successfully!";
    }

    @Override
    public Post viewPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(()-> new PostNotFound("This post is not found!"));

        return post;
    }

    @Override
    public String updatePost(Long id, PostDto postDto) {
        Long userId = util.getLoggedInUser();
        User user = util.findUserById(userId);

        if (user.getRole().equals(Role.CUSTOMER)){
            throw new PermissionDenied("Permission denied!");
        }

        Post post = postRepository.findById(id)
                .orElseThrow(()-> new PostNotFound("This post is not found!"));

        if (Objects.nonNull(postDto.getTitle()) && !postDto.getTitle().equals(""))
            post.setTitle(postDto.getTitle());
        if (Objects.nonNull(postDto.getImgUrl()) && !postDto.getImgUrl().equals(""))
            post.setImgUrl(postDto.getImgUrl());
        if (Objects.nonNull(postDto.getContent()) && !postDto.getContent().equals(""))
            post.setContent(postDto.getContent());

        postRepository.save(post);

        return "Post updated successfully!";
    }

    @Override
    public String deletePost(Long id) {
        Long userId = util.getLoggedInUser();
        User user = util.findUserById(userId);

        if (user.getRole().equals(Role.CUSTOMER)){
            throw new PermissionDenied("You are not an admin");
        }

        Post post = postRepository.findById(id)
                .orElseThrow(()-> new PostNotFound("Post not found!"));

        postRepository.delete(post);

        return "Post deleted successfully!";
    }


}
