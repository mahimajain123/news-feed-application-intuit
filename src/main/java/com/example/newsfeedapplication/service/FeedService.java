package com.example.newsfeedapplication.service;

import com.example.newsfeedapplication.dto.FeedRequest;
import com.example.newsfeedapplication.entity.Post;
import com.example.newsfeedapplication.entity.User;
import com.example.newsfeedapplication.repository.mockDB.UserFollowRelationshipDB;
import com.example.newsfeedapplication.repository.mockDB.PostDB;
import com.example.newsfeedapplication.repository.mockDB.UserDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FeedService {

    @Autowired
    private PostMetadataService postMetadataService;
    @Autowired
    private UserFollowRelationshipDB followRepositoryDB;

    @Autowired
    private PostDB postDB;

    @Autowired
    private UserDB userDB;

    @Autowired
    private UserInterestsService userInterestsService;

    @Autowired
    private FeedCachingService feedCachingService;

    @Autowired
    private PostService postService;

    public List<Post> getFeed(FeedRequest feedRequest) {
        try {
            Long userId = feedRequest.getUserId();

            String location = feedRequest.getLocation();

            List<String> hashTags = userInterestsService.getUserInterests(userId);

            List<String> systemTags = feedRequest.getSystemTags();

            // Get trending posts by user interests, system tag, location from cache
            Set<String> combinedTags = new HashSet<>();
            combinedTags.addAll(systemTags);
            combinedTags.addAll(hashTags);

            List<Post> feedPosts = new ArrayList<>();
            feedPosts = feedCachingService.getTrendingPostsForUser(combinedTags, location);

            if (Objects.nonNull(feedPosts) && !feedPosts.isEmpty()) {
                return feedPosts;
            }
            //If cache is empty, get trending posts from ES and DB

            List<Long> userFeedPostIdsFromES = postMetadataService.getUserFeedPostIdsFromES(
                    hashTags, systemTags, location, userId);

            List<User> followingList = followRepositoryDB.getFollowers(userId);

            List<Long> userIds = followingList.stream().map(User::getUserId).collect(Collectors.toList());
            List<Long> postsByFollowing = postService.getPostIdByUserIds(userIds);

            // Combining the post IDs from Elasticsearch and the database
            Set<Long> userFeedPostIds = new HashSet<>();
            userFeedPostIds.addAll(userFeedPostIdsFromES);
            userFeedPostIds.addAll(postsByFollowing);

            // Fetching the posts by their IDs
            feedPosts = postService.getPostsByIds(userFeedPostIds.stream().toList());

            return feedPosts;
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while fetching feed", e);
        }
    }
}
