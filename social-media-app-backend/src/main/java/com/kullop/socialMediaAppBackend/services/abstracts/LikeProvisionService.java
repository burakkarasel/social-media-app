package com.kullop.socialMediaAppBackend.services.abstracts;

import com.kullop.socialMediaAppBackend.requests.LikeCreateRequest;
import org.springframework.http.ResponseEntity;

public interface LikeProvisionService {
    ResponseEntity<Object> createLike(LikeCreateRequest likeCreateRequest);
    ResponseEntity<Object> deleteLikeById(Long id);
}
