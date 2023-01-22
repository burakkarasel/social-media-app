package com.kullop.socialMediaAppBackend.services.concretes;

import com.kullop.socialMediaAppBackend.entities.User;
import com.kullop.socialMediaAppBackend.repositories.concretes.UserDao;
import com.kullop.socialMediaAppBackend.services.abstracts.UserQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserQueryManager implements UserQueryService {

    private UserDao userDao;

    public UserQueryManager(UserDao _userDao){
        this.userDao = _userDao;
    }

    @Override
    public ResponseEntity<Object> getAllUsers() {
        List<User> users = this.userDao.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getUserById(Long userId) {
        Optional<User> user = this.userDao.getUserById(userId);
        if(user.isEmpty()){
            HashMap<String, String > response = new HashMap<>();
            response.put("message", "user not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);

    }
}
