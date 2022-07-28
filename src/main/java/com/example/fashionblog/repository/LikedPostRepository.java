package com.example.fashionblog.repository;

import com.example.fashionblog.model.LikedPost;
import com.example.fashionblog.model.Post;
import com.example.fashionblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikedPostRepository extends JpaRepository<LikedPost, Long> {

    @Query("SELECT COUNT(lp) > 0 FROM LikedPost lp WHERE lp.user = :user AND lp.post = :post")
    boolean likedPostExistsByUserAndPost(User user, Post post);

    @Modifying
    @Query("DELETE FROM LikedPost lp WHERE lp.user = :user AND lp.post = :post")
    void deleteLikedPostByUserAndPost(User user, Post post);


}
