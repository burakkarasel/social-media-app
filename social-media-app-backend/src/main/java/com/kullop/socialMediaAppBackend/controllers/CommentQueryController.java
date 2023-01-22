package com.kullop.socialMediaAppBackend.controllers;

import com.kullop.socialMediaAppBackend.services.abstracts.CommentQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentQueryController {
    private CommentQueryService commentQueryService;

    public CommentQueryController(CommentQueryService _commentQueryService){
        this.commentQueryService = _commentQueryService;
    }

    @GetMapping
    public ResponseEntity<Object> getComments(@RequestParam Optional<Long> postId, @RequestParam Optional<Long> userId){
        if(postId.isPresent() && userId.isPresent()){
            return this.commentQueryService.getCommentsByPostIdAndUserId(postId.get(), userId.get());
        }
        if(postId.isPresent()){
            return this.commentQueryService.getCommentsByPostId(postId.get());
        }
        if(userId.isPresent()){
            return this.commentQueryService.getCommentsByUserId(userId.get());
        }
        return this.commentQueryService.getAllComments();
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<Object> getCommentById(@PathVariable Long commentId){
        return this.commentQueryService.getCommentById(commentId);
    }
}
