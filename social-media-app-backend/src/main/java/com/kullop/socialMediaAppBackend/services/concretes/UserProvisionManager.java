package com.kullop.socialMediaAppBackend.services.concretes;

import com.kullop.socialMediaAppBackend.entities.User;
import com.kullop.socialMediaAppBackend.repositories.concretes.UserDao;
import com.kullop.socialMediaAppBackend.services.abstracts.UserProvisionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserProvisionManager implements UserProvisionService {
    private UserDao userDao;

    public UserProvisionManager(UserDao _userDao){
        this.userDao = _userDao;
    }

    @Override
    public User createUser(User user) {
        return this.userDao.createUser(user);
    }

    @Override
    public ResponseEntity<Object> updateUserById(Long id, User user) {
        Boolean userExists = this.userDao.isUserExist(id);
        HashMap<String, String> response = new HashMap<>();

        if(!userExists){
            response.put("message", "user not found");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
        user.setId(id);
        this.userDao.updateUserById(user);

        response.put("message", "success");
        return new ResponseEntity<>(response ,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> deleteUserById(Long id) {
        Boolean userExists = this.userDao.isUserExist(id);
        HashMap<String, String> response = new HashMap<>();

        if(!userExists){
            response.put("message", "user not found");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
        this.userDao.deleteUserById(id);
        response.put("message", "success");
        return new ResponseEntity<>(response ,HttpStatus.OK);
    }


}
