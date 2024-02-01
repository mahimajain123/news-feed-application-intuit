package com.example.newsfeedapplication.controller;

import com.example.newsfeedapplication.entity.User;
import com.example.newsfeedapplication.repository.UserRepository;
import com.example.newsfeedapplication.repository.mockDB.UserDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
public class UserController {


    private final UserDB userDB;

    @Autowired
    public UserController(UserDB userDB) {

        this.userDB = userDB;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userDB.save(user);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        User user = userDB.findById(id);
        if (user == null) {
            throw new RuntimeException("User not found with id " + id);
        }
        return user;
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        User user = userDB.findById(id);
        if(user == null) {
            throw new RuntimeException("User not found with id " + id);
        }
        return userDB.update(updatedUser);

    }
}