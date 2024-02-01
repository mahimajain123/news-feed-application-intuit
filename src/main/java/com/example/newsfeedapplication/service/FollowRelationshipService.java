package com.example.newsfeedapplication.service;

import com.example.newsfeedapplication.entity.User;
import com.example.newsfeedapplication.entity.UserFollowRelationship;
import com.example.newsfeedapplication.enums.FollowingStatus;
import com.example.newsfeedapplication.enums.UserPrivacyStatus;
import com.example.newsfeedapplication.repository.UserRepository;
import com.example.newsfeedapplication.repository.mockDB.UserDB;
import com.example.newsfeedapplication.repository.mockDB.UserFollowRelationshipDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowRelationshipService {

    private final UserFollowRelationshipDB userFollowRelationshipDB;

    private final UserDB userDB;

    @Autowired
    public FollowRelationshipService(UserFollowRelationshipDB userFollowRelationshipDB, UserDB userDB) {
        this.userFollowRelationshipDB = userFollowRelationshipDB;
        this.userDB = userDB;
    }

    public UserFollowRelationship createFollowRelationship(User followerUserId, User followedUserId) {
        User followedUser = userDB.findById(followedUserId.getUserId());

        if(followedUser == null) {
            throw new RuntimeException("User not found with id " + followedUserId);
        }

        if (followedUser.getPrivacyStatus() == UserPrivacyStatus.PRIVATE) {
            throw new RuntimeException("Cannot follow user with private status");
        }

        FollowingStatus followingStatus = followedUser.getPrivacyStatus() == UserPrivacyStatus.SEMI_PRIVATE
                ? FollowingStatus.PENDING
                : FollowingStatus.FOLLOWING;

        UserFollowRelationship followRelationship = new UserFollowRelationship(followerUserId, followedUserId, followingStatus);
        return userFollowRelationshipDB.save(followRelationship);
    }

    public List<UserFollowRelationship> getPendingRequests(Long userId) {
        User user = userDB.findById(userId);

        if(user == null) {
            throw new RuntimeException("User not found with id " + userId);
        }

        if (user.getPrivacyStatus() != UserPrivacyStatus.SEMI_PRIVATE) {
            throw new RuntimeException("User does not have SEMI_PRIVATE privacy status");
        }

        return userFollowRelationshipDB.findByFollowedUserIdAndFollowingStatus(userId, FollowingStatus.PENDING);
    }

    public UserFollowRelationship updateFollowStatus(Long followRelationshipId, FollowingStatus status) {
        UserFollowRelationship followRelationship = userFollowRelationshipDB.findById(followRelationshipId);
        if(followRelationship == null) {
            throw new RuntimeException("Follow relationship not found with id " + followRelationshipId);
        }

        followRelationship.setFollowingStatus(status);
        return userFollowRelationshipDB.save(followRelationship);
    }
}