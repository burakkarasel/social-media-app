package com.project.app.services;

import com.project.app.entities.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();
    public User createUser(User user);
    public User getUserById(long userId);

    public void deleteUserById(long userId);

    public User updateUserById(long userId, User user);
}
