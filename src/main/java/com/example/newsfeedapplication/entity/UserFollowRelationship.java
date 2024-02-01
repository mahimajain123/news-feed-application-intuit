package com.example.newsfeedapplication.entity;

import javax.persistence.*;

import com.example.newsfeedapplication.enums.FollowingStatus;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "FollowRelationship")
@Getter
@Setter
public class UserFollowRelationship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "follower_user_id", referencedColumnName = "user_id")
    private User followerUserId;

    @ManyToOne
    @JoinColumn(name = "followed_user_id", referencedColumnName = "user_id")
    private User followedUserId;

    @Enumerated(EnumType.STRING)
    @Column(name = "following_status")
    private FollowingStatus followingStatus;

    // Constructors
    public UserFollowRelationship() {
    }

    public UserFollowRelationship(User followerUserId, User followedUserId, FollowingStatus followingStatus) {
        this.followerUserId = followerUserId;
        this.followedUserId = followedUserId;
        this.followingStatus = followingStatus;
    }
}

