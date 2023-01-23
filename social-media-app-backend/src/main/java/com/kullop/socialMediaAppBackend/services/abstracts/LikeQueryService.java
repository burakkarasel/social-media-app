package com.kullop.socialMediaAppBackend.services.abstracts;

import org.springframework.http.ResponseEntity;

public interface LikeQueryService {
    ResponseEntity<Object> getAllLikes();
    ResponseEntity<Object> getLikesByPostId(Long postId);
    ResponseEntity<Object> getLikesByUserId(Long userId);
    ResponseEntity<Object> getLikesByPostIdAndUserId(Long postId, Long userId);
    ResponseEntity<Object> getLikeById(Long id);
}
