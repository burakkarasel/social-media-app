package com.project.app.services;

import com.project.app.entities.User;
import com.project.app.repos.UserRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Setter
public class UserManager implements UserService{

    private UserRepository userRepository;

    @Autowired
    public UserManager(UserRepository _userRepository){
        this.userRepository = _userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User getUserById(long userId) {
        return this.userRepository.findById(userId).orElse(null);
    }

    @Override
    public void deleteUserById(long userId) {
        this.userRepository.deleteById(userId);
    }

    @Override
    public User updateUserById(long userId, User newUser) {
        User user = this.userRepository.findById(userId).orElse(null);
        if(user == null){
            return null;
        }
        newUser.setId(userId);
        return this.userRepository.save(newUser);
    }
}
