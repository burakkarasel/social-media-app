package com.kullop.socialMediaAppBackend.services.concretes;

import com.kullop.socialMediaAppBackend.entities.User;
import com.kullop.socialMediaAppBackend.repositories.concretes.UserDao;
import com.kullop.socialMediaAppBackend.requests.UserRequest;
import com.kullop.socialMediaAppBackend.responses.UserResponse;
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
    public ResponseEntity<Object> createUser(UserRequest userRequest) {
        User createdUser = this.userDao.createUser(new User(userRequest));
        return new ResponseEntity<>(new UserResponse(createdUser), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> updateUserById(Long id, UserRequest userRequest) {
        HashMap<String, String> response = new HashMap<>();
        if(!this.userDao.isUserExist(id)){
            response.put("message", "user not found");
            return new ResponseEntity<>( response ,HttpStatus.NOT_FOUND);
        }
        User toUpdate = new User(userRequest);
        this.userDao.updateUserById(toUpdate, id);
        response.put("message", "success");
        return new ResponseEntity<>(response ,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> deleteUserById(Long id) {
        HashMap<String, String> response = new HashMap<>();
        if(!this.userDao.isUserExist(id)){
            response.put("message", "user not found");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
        this.userDao.deleteUserById(id);
        response.put("message", "success");
        return new ResponseEntity<>(response ,HttpStatus.OK);
    }


}
