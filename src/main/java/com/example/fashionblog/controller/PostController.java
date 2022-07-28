package com.example.fashionblog.controller;

import com.example.fashionblog.dto.PostDto;
import com.example.fashionblog.model.Category;
import com.example.fashionblog.model.Post;
import com.example.fashionblog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PostController {

    private final PostService postService;

    @PostMapping("/admin/create/{id}")
    public ResponseEntity<String> creatPost(@PathVariable("id") Long id, @RequestBody PostDto postDto){
        return ResponseEntity.ok(postService.createPost(id,postDto));
    }
    @GetMapping("/view-post/{id}")
    public ResponseEntity<Post> viewPostById(@PathVariable("id") Long id){
        return ResponseEntity.ok(postService.viewPostById(id));
    }

    @PutMapping("/admin/update-post/{id}")
    public ResponseEntity<String> updatePost(@PathVariable Long id, @RequestBody PostDto postDto){
        return ResponseEntity.ok(postService.updatePost(id, postDto));
    }

    @DeleteMapping("/admin/delete-post/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id){
        return ResponseEntity.ok(postService.deletePost(id));
    }
}
