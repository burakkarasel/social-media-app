package com.kullop.socialMediaAppBackend.services.abstracts;

import com.kullop.socialMediaAppBackend.entities.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserQueryService {
    public ResponseEntity<Object> getAllUsers();
    public ResponseEntity<Object> getUserById(Long userId);
}
