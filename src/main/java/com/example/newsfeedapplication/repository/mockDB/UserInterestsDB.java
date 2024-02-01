package com.example.newsfeedapplication.repository.mockDB;

import com.example.newsfeedapplication.entity.User;
import com.example.newsfeedapplication.entity.UserInterests;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@DependsOn({"userDB"})
public class UserInterestsDB {

    @Autowired
    private final UserDB userDB;

    @Getter
    private final List<UserInterests> userInterestsList;

    public UserInterestsDB(UserDB userDB) {
        this.userDB = userDB;
        userInterestsList = new ArrayList<>();
        User user = userDB.getUsers().get(0);
        List<String> interests = Arrays.asList("Coding", "Health", "Traveling", "Politics");
        userInterestsList.add(new UserInterests(1L, user, interests, true));
    }

    public UserInterests save(UserInterests userInterests) {
        this.userInterestsList.add(userInterests);
        return userInterests;
    }

    public UserInterests update(UserInterests userInterests) {
        UserInterests userInterestsToUpdate = findById(userInterests.getId());

        userInterestsToUpdate.setInterests(userInterests.getInterests());

        userInterestsList.replaceAll(u -> u.getId().equals(userInterests.getId()) ? userInterests : u);

        return userInterestsToUpdate;
    }

    public UserInterests findByUserId(Long userId) {
        for (UserInterests userInterest : userInterestsList) {
            if (userInterest.getUser().getUserId().equals(userId)) {
                return userInterest;
            }
        }
        return null;
    }

    public UserInterests findById(Long id) {
        for (UserInterests userInterest : userInterestsList) {
            if (id.equals(userInterest.getId())) {
                return userInterest;
            }
        }
        return null;
    }
}