package com.example.fashionblog.controller;

import com.example.fashionblog.dto.CommentDto;
import com.example.fashionblog.model.Comment;
import com.example.fashionblog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/add-comment/{id}")
    public ResponseEntity<Comment> writeComment(@PathVariable("id") Long id, @RequestBody CommentDto commentDto){
        return ResponseEntity.ok(commentService.writeComment(id,commentDto));
    }


    @PatchMapping("/update-comment/{id}")
    public ResponseEntity<Comment> editComment(@PathVariable("id") Long id, @RequestBody CommentDto commentDto){
        return ResponseEntity.ok(commentService.editComment(id, commentDto));
    }

    @GetMapping("/view-comment/{id}")
    public ResponseEntity<Comment> viewCommentById(@PathVariable("id") Long id){
        return ResponseEntity.ok(commentService.viewCommentById(id));
    }

    @DeleteMapping("/delete-comment/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable("id") Long id){
        return ResponseEntity.ok(commentService.deleteComment(id));
    }
}
