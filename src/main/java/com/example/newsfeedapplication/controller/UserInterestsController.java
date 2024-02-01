package com.example.newsfeedapplication.controller;

import com.example.newsfeedapplication.entity.UserInterests;
import com.example.newsfeedapplication.repository.mockDB.UserInterestsDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user-interests")
public class UserInterestsController {

    private final UserInterestsDB userInterestsDB;

    @Autowired
    public UserInterestsController(UserInterestsDB userInterestsDB) {
        this.userInterestsDB = userInterestsDB;
    }

    @PostMapping
    public UserInterests createUserInterests(@RequestBody UserInterests userInterests) {
        return userInterestsDB.save(userInterests);
    }

    @PutMapping("/{id}")
    public UserInterests updateUserInterests(@PathVariable Long id, @RequestBody UserInterests updatedUserInterests) {
        UserInterests userInterests = userInterestsDB.findByUserId(id);
        if (userInterests != null) {
            return userInterestsDB.update(updatedUserInterests);
        } else {
            throw new RuntimeException("User interests not found with id " + id);
        }
    }
}