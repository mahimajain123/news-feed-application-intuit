package com.example.newsfeedapplication.service;

import com.example.newsfeedapplication.entity.UserInterests;

import com.example.newsfeedapplication.repository.mockDB.UserInterestsDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserInterestsService {

    private final UserInterestsDB userInterestsDB;

    @Autowired
    public UserInterestsService(UserInterestsDB userInterestsDB) {
        this.userInterestsDB = userInterestsDB;
    }

    public List<String> getUserInterests(Long userId) {
        UserInterests userInterests = userInterestsDB.findByUserId(userId);
        if (userInterests != null) {
            return userInterests.getInterests();
        } else {
            return new ArrayList<>();
        }
    }
}