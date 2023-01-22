package com.kullop.socialMediaAppBackend.controllers;

import com.kullop.socialMediaAppBackend.entities.User;
import com.kullop.socialMediaAppBackend.services.abstracts.UserQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserQueryController {
    private UserQueryService userQueryService;

    public UserQueryController(UserQueryService _userQueryService){
        this.userQueryService = _userQueryService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllUsers(){
        return this.userQueryService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable Long userId){
        return this.userQueryService.getUserById(userId);
    }
}
