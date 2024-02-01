package com.example.newsfeedapplication.service;

import com.example.newsfeedapplication.entity.UserInteraction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserInteractionService {
    private List<UserInteraction> userInteractions;

    UserInteractionService() {
        this.userInteractions = new ArrayList<>();
    }

    public void addUserInteraction(UserInteraction userInteraction) {
        this.userInteractions.add(userInteraction);
    }

}
