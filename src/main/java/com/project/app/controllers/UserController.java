package com.project.app.controllers;

import com.project.app.entities.User;
import com.project.app.services.UserService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Setter
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService _userService){
        this.userService =_userService;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return this.userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        return this.userService.createUser(user);
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable long userId){
        return this.userService.getUserById(userId);
    }

    @PutMapping("/{userId}")
    public User updateUserById(@PathVariable Long userId, @RequestBody User newUser){
        return this.userService.updateUserById(userId, newUser);
    }

    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable long userId){
        this.userService.deleteUserById(userId);
    }
}
