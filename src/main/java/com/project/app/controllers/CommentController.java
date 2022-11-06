package com.project.app.controllers;

import com.project.app.entities.Comment;
import com.project.app.requests.CommentCreateRequest;
import com.project.app.requests.CommentUpdateRequest;
import com.project.app.services.CommentService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
@Setter
public class CommentController {
    private CommentService commentService;

    @Autowired
    public CommentController(CommentService _commentService){
        this.commentService = _commentService;
    }

    @GetMapping
    public List<Comment> getComments(@RequestParam Optional<Long> postId, @RequestParam Optional<Long> userId) {
        return this.commentService.getComments(postId, userId);
    }

    @PostMapping
    public Comment createComment(@RequestBody CommentCreateRequest newComment){
        return this.commentService.createComment(newComment);
    }

    @GetMapping("/{commentId}")
    public Comment getCommentById(@PathVariable Long commentId){
        return this.commentService.getCommentById(commentId);
    }

    @PutMapping("/{commentId}")
    public Comment updateCommentById(@PathVariable Long commentId, @RequestBody CommentUpdateRequest newComment){
        return this.commentService.updateCommentById(commentId, newComment);
    }

    @DeleteMapping("/{commentId}")
    public void deleteCommentById(@PathVariable Long commentId){
        this.commentService.deleteCommentById(commentId);
    }
}
