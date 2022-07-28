package com.example.fashionblog.controller;

import com.example.fashionblog.model.Post;
import com.example.fashionblog.service.LikedPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class LikedPostController {

    private final LikedPostService likedPostService;

    @PostMapping("/liked-post/{id}")
    public ResponseEntity<String> likedPost(@PathVariable Long id){
        return ResponseEntity.ok(likedPostService.likedPost(id));
    }

    @DeleteMapping("/unlike-post/{id}")
    public ResponseEntity<String> unlikePost(@PathVariable Long id){
        return ResponseEntity.ok(likedPostService.unlikePost(id));
    }
}
