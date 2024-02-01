package com.example.newsfeedapplication.controller;

import com.example.newsfeedapplication.dto.FeedRequest;
import com.example.newsfeedapplication.entity.Post;
import com.example.newsfeedapplication.repository.mockDB.UserFollowRelationshipDB;
import com.example.newsfeedapplication.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/feed")
public class FeedController {

    @Autowired
    private UserFollowRelationshipDB followRepositoryDB;

    @Autowired
    private FeedService feedService;

    @GetMapping
    List<Post> getFeed(@RequestBody FeedRequest feedRequest) {
        try {
            return feedService.getFeed(feedRequest);
        } catch (Exception e) {
            throw new FeedServiceException(
                    "Oops! There's seems to be an error in showing your feed, we will be back shortly.", e);
        }
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({ FeedServiceException.class })
    public void handleException() {
    }

    public static class FeedServiceException extends RuntimeException {
        public FeedServiceException(String message, Throwable cause) {
            super(message, cause);
        }
    }

}
