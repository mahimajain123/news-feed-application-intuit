package com.example.newsfeedapplication.controller;

import com.example.newsfeedapplication.enums.FollowingStatus;
import com.example.newsfeedapplication.entity.UserFollowRelationship;
import com.example.newsfeedapplication.service.FollowRelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/follow-relationships")
public class FollowRelationshipController {

    private final FollowRelationshipService followRelationshipService;

    @Autowired
    public FollowRelationshipController(FollowRelationshipService followRelationshipService) {
        this.followRelationshipService = followRelationshipService;
    }

    @PostMapping
    public UserFollowRelationship createFollowRelationship(@RequestBody UserFollowRelationship followRelationship) {
        return followRelationshipService.createFollowRelationship(followRelationship.getFollowerUserId(), followRelationship.getFollowedUserId());
    }

    @GetMapping("/{id}/pending-requests")
    public List<UserFollowRelationship> getPendingRequests(@PathVariable Long id) {
        return followRelationshipService.getPendingRequests(id);
    }

    @PutMapping("/follow-relationships/{id}")
    public UserFollowRelationship updateFollowStatus(@PathVariable Long id, @RequestBody FollowingStatus status) {
        return followRelationshipService.updateFollowStatus(id, status);
    }
}