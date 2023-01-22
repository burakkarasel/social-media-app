package com.kullop.socialMediaAppBackend.controllers;

import com.kullop.socialMediaAppBackend.entities.User;
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
    public User createUser(@RequestBody User user){
        return this.userProvisionService.createUser(user);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<Object> updateUserById(@PathVariable Long userId, @RequestBody User user){
        return this.userProvisionService.updateUserById(userId, user);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUserById(@PathVariable Long userId){
        return this.userProvisionService.deleteUserById(userId);
    }
}
