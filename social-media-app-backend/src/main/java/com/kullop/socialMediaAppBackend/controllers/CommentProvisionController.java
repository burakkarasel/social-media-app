package com.kullop.socialMediaAppBackend.controllers;

import com.kullop.socialMediaAppBackend.requests.CommentCreateRequest;
import com.kullop.socialMediaAppBackend.requests.CommentUpdateRequest;
import com.kullop.socialMediaAppBackend.services.abstracts.CommentProvisionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentProvisionController {
    private CommentProvisionService commentProvisionService;

    public CommentProvisionController(CommentProvisionService _commentProvisionService){
        this.commentProvisionService = _commentProvisionService;
    }

    @PostMapping
    public ResponseEntity<Object> createComment(@RequestBody CommentCreateRequest commentCreateRequest){
        return this.commentProvisionService.createComment(commentCreateRequest);
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<Object> updateCommentById(@PathVariable Long commentId, @RequestBody CommentUpdateRequest commentUpdateRequest){
        return this.commentProvisionService.updateCommentById(commentId, commentUpdateRequest);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Object> deleteCommentById(@PathVariable Long commentId){
        return this.commentProvisionService.deleteCommentById(commentId);
    }
}
