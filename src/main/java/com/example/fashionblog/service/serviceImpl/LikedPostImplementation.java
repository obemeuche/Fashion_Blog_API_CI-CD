package com.example.fashionblog.service.serviceImpl;

import com.example.fashionblog.exception.PostNotFound;
import com.example.fashionblog.exception.UserAlreadyLikedPost;
import com.example.fashionblog.exception.UserNotFound;
import com.example.fashionblog.model.LikedPost;
import com.example.fashionblog.model.Post;
import com.example.fashionblog.model.User;
import com.example.fashionblog.repository.LikedPostRepository;
import com.example.fashionblog.repository.PostRepository;
import com.example.fashionblog.service.LikedPostService;
import com.example.fashionblog.utils.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@RequiredArgsConstructor
public class LikedPostImplementation implements LikedPostService {

    private final PostRepository postRepository;

    private final SessionUtil util;

    private final LikedPostRepository likedPostRepository;

    @Override
    public String likedPost(Long id) {
        Long userId = util.getLoggedInUser();
        User user = util.findUserById(userId);

        if (user == null){
            throw new UserNotFound("You are not logged in");
        }


        Post post = postRepository.findById(id)
                .orElseThrow(()-> new PostNotFound("Post not found"));

       if (likedPostRepository.likedPostExistsByUserAndPost(user, post))
           throw new UserAlreadyLikedPost("User already liked this post");

         LikedPost likedPost1 = LikedPost.builder()
                 .post(post)
                 .user(user)
                 .build();

        post.getLikedPost().add(likedPost1);
        postRepository.save(post);

        return "Post liked!";
    }

    @Override
    @Transactional
    public String unlikePost(Long id) {
        Long userId = util.getLoggedInUser();
        User user = util.findUserById(userId);

        if (user == null){
            throw new UserNotFound("You are not logged in");
        }


        Post post = postRepository.findById(id)
                .orElseThrow(()-> new PostNotFound("Post not found"));

        likedPostRepository.deleteLikedPostByUserAndPost(user, post);
//                .orElseThrow(() -> new RuntimeException("User never liked this post"));


    return "Post successfully unliked!";


    }


}
