package com.kullop.socialMediaAppBackend.services.abstracts;

import org.springframework.http.ResponseEntity;

public interface CommentQueryService {
    ResponseEntity<Object> getAllComments();
    ResponseEntity<Object> getCommentById(Long id);
    ResponseEntity<Object> getCommentsByPostId(Long postId);
    ResponseEntity<Object> getCommentsByUserId(Long userId);
    ResponseEntity<Object> getCommentsByPostIdAndUserId(Long postId, Long userId);
}
