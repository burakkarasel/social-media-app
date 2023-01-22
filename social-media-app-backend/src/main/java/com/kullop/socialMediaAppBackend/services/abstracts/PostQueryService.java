package com.kullop.socialMediaAppBackend.services.abstracts;

import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface PostQueryService {
    public ResponseEntity<Object> getAllPosts(Optional<Long> userId);
    public ResponseEntity<Object> getPostById(Long postId);
}
