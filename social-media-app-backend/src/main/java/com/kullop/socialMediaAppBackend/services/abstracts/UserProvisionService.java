package com.kullop.socialMediaAppBackend.services.abstracts;

import com.kullop.socialMediaAppBackend.entities.User;
import org.springframework.http.ResponseEntity;

public interface UserProvisionService {
    public ResponseEntity<Object> createUser(User user);

    public ResponseEntity<Object> updateUserById(Long id, User user);

    public ResponseEntity<Object> deleteUserById(Long id);
}
