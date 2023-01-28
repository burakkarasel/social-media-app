package com.kullop.socialMediaAppBackend.repositories.concretes;

import com.kullop.socialMediaAppBackend.entities.User;
import com.kullop.socialMediaAppBackend.repositories.abstracts.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDao{
    private UserRepository userRepository;

    public UserDao(UserRepository _userRepository){
        this.userRepository = _userRepository;
    }

    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    public User createUser(User user){
        return this.userRepository.save(user);
    }

    public Optional<User> getUserById(Long userId){
        return this.userRepository.findById(userId);
    }

    @Transactional
    public void updateUserById(User user, Long userId){
        this.userRepository.updateUserById(user.getUsername(), user.getPassword(), userId);
    }

    public Boolean isUserExist(Long userId){
        return this.userRepository.existsById(userId);
    }


    public void deleteUserById(Long userId){
        this.userRepository.deleteById(userId);
    }

    public User findByUsername(String username){
        return this.userRepository.findByUsername(username);
    }
}
