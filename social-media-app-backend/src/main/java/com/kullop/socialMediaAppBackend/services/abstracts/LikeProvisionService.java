package com.kullop.socialMediaAppBackend.services.abstracts;

import com.kullop.socialMediaAppBackend.requests.LikeCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public interface LikeProvisionService {
    ResponseEntity<Object> createLike(LikeCreateRequest likeCreateRequest);
    ResponseEntity<Object> deleteLikeById(Long id);
}
