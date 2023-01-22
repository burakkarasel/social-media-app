package com.kullop.socialMediaAppBackend.controllers;

import com.kullop.socialMediaAppBackend.entities.User;
import com.kullop.socialMediaAppBackend.requests.UserRequest;
import com.kullop.socialMediaAppBackend.services.abstracts.UserProvisionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserProvisionController {
    private UserProvisionService userProvisionService;

    public UserProvisionController(UserProvisionService _userProvisionService){
        this.userProvisionService = _userProvisionService;
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody UserRequest userRequest){
        return this.userProvisionService.createUser(userRequest);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<Object> updateUserById(@PathVariable Long userId, @RequestBody UserRequest userRequest){
        return this.userProvisionService.updateUserById(userId, userRequest);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUserById(@PathVariable Long userId){
        return this.userProvisionService.deleteUserById(userId);
    }
}
