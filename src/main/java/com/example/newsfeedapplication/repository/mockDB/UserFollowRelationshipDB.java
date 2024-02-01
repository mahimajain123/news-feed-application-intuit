package com.example.newsfeedapplication.repository.mockDB;

import com.example.newsfeedapplication.enums.FollowingStatus;
import com.example.newsfeedapplication.entity.UserFollowRelationship;
import com.example.newsfeedapplication.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@DependsOn({"userDB"})
public class UserFollowRelationshipDB {

    private final List<UserFollowRelationship> followRelationshipList;
    @Autowired
    private final UserDB userDB;

    UserFollowRelationshipDB(List<UserFollowRelationship> followRelationshipList, UserDB userDB){
        this.followRelationshipList = followRelationshipList;
        this.userDB = userDB;
        createMockEntries(5);
    }

    public void createMockEntries(int numberOfEntries) {

        for (int i = 0; i < numberOfEntries; i++) {
            User follower = userDB.getUsers().get(i);
            User followed = userDB.getUsers().get(i + 1);
            UserFollowRelationship mockRelationship = new UserFollowRelationship(follower, followed, FollowingStatus.FOLLOWING);
            followRelationshipList.add(mockRelationship);
        }
    }
    public List<User> getFollowers(Long userId) {
        List<Long> listUserId = new ArrayList<>();
        for (UserFollowRelationship followRelationship : followRelationshipList) {
            if (followRelationship.getFollowedUserId().getUserId().equals(userId)) {
                listUserId.add(followRelationship.getFollowerUserId().getUserId());
            }
        }

        return listUserId.stream().map(userDB::getUserByUserId).toList();
    }

    public UserFollowRelationship save(UserFollowRelationship followRelationship) {
        followRelationshipList.add(followRelationship);
        return followRelationship;
    }

    public List<UserFollowRelationship> findByFollowedUserIdAndFollowingStatus(Long userId, FollowingStatus status) {
        List<UserFollowRelationship> result = new ArrayList<>();
        for (UserFollowRelationship followRelationship : followRelationshipList) {
            if (followRelationship.getFollowedUserId().getUserId().equals(userId) && followRelationship.getFollowingStatus().equals(status)) {
                result.add(followRelationship);
            }
        }
        return result;
    }

    public UserFollowRelationship findById(Long followRelationshipId) {
        for (UserFollowRelationship followRelationship : followRelationshipList) {
            if (followRelationship.getId().equals(followRelationshipId)) {
                return followRelationship;
            }
        }
        return null;
    }
}
