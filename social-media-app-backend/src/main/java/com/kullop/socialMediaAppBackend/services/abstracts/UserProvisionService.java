package com.kullop.socialMediaAppBackend.services.abstracts;

import com.kullop.socialMediaAppBackend.requests.UserRequest;
import org.springframework.http.ResponseEntity;

public interface UserProvisionService {
    public ResponseEntity<Object> createUser(UserRequest userRequest);

    public ResponseEntity<Object> updateUserById(Long id, UserRequest userRequest);

    public ResponseEntity<Object> deleteUserById(Long id);
}
