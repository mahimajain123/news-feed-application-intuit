package com.example.newsfeedapplication.repository;

import com.example.newsfeedapplication.enums.FollowingStatus;
import com.example.newsfeedapplication.entity.UserFollowRelationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface FollowRelationshipRepository
//        extends JpaRepository<UserFollowRelationship, Long>
{
    List<UserFollowRelationship> findByFollowedUserIdAndFollowingStatus(Long userId, FollowingStatus followingStatus);
}