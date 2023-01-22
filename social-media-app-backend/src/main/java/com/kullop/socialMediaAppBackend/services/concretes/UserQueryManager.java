package com.kullop.socialMediaAppBackend.services.concretes;

import com.kullop.socialMediaAppBackend.entities.User;
import com.kullop.socialMediaAppBackend.repositories.concretes.UserDao;
import com.kullop.socialMediaAppBackend.services.abstracts.UserQueryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserQueryManager implements UserQueryService {

    private UserDao userDao;

    public UserQueryManager(UserDao _userDao){
        this.userDao = _userDao;
    }

    @Override
    public List<User> getAllUsers() {
        return this.userDao.getAllUsers();
    }

    @Override
    public User getUserById(Long userId) {
        return this.userDao.getUserById(userId);
    }
}
