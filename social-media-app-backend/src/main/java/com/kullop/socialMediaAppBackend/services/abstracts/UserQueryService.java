package com.kullop.socialMediaAppBackend.services.abstracts;

import com.kullop.socialMediaAppBackend.entities.User;

import java.util.List;

public interface UserQueryService {
    public List<User> getAllUsers();
    public User getUserById(Long userId);
}
