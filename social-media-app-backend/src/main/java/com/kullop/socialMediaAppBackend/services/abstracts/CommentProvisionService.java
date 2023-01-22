package com.kullop.socialMediaAppBackend.services.abstracts;

import com.kullop.socialMediaAppBackend.requests.CommentCreateRequest;
import com.kullop.socialMediaAppBackend.requests.CommentUpdateRequest;
import org.springframework.http.ResponseEntity;

public interface CommentProvisionService {
    ResponseEntity<Object> createComment(CommentCreateRequest commentCreateRequest);
    ResponseEntity<Object> updateCommentById(Long commentId, CommentUpdateRequest commentUpdateRequest);
    ResponseEntity<Object> deleteCommentById(Long commentId);
}
