package com.kullop.socialMediaAppBackend.services.abstracts;

import com.kullop.socialMediaAppBackend.requests.PostCreateRequest;
import com.kullop.socialMediaAppBackend.requests.PostUpdateRequest;
import org.springframework.http.ResponseEntity;

public interface PostProvisionService {
    public ResponseEntity<Object> createPost(PostCreateRequest post);

    public ResponseEntity<Object> updatePostById(PostUpdateRequest postUpdateRequest, Long postId);

    public ResponseEntity<Object> deletePostById(Long postId);
}
