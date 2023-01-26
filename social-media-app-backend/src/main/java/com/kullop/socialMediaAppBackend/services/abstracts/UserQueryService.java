package com.kullop.socialMediaAppBackend.services.abstracts;

import org.springframework.http.ResponseEntity;


public interface UserQueryService {
    public ResponseEntity<Object> getAllUsers();
    public ResponseEntity<Object> getUserById(Long userId);
}
