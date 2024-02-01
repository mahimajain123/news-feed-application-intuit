package com.example.newsfeedapplication.service;

import com.example.newsfeedapplication.entity.Post;
import com.example.newsfeedapplication.enums.PostType;
import com.example.newsfeedapplication.repository.mockDB.PostDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This service class is used to get all trending posts from Redis cache.
 * The cache is based on hashtags, system tags and location.
 * Posts IDs are stored for each hashtag, system tag and location.
 * Post Info will be stored as separate key value pair in Redis. key -> postId, value -> postInfo
 * We are setting cache using a microservice which polls data from ES every 30 secs and refreshes the cache.
 * Hashtag, system tag and location keys gets refreshed every time the cache is updated.
 * TTL for Post Info keys is 60 minutes.
 **/
@Service
public class FeedCachingService {

    public static final String HASHTAG_TRENDING_POST_PREFIX = "hashtag";

    public static final String SYSTEMTAG_TRENDING_POST_PREFIX = "systemTag";

    public static final String LOCATION_TRENDING_POST_PREFIX = "location";

    public static final String TRENDING_POST_PREFIX = "trendingPost";

    public static final String redisKeyDelimiter = "#";

    private final PostDB postDB;

    @Autowired
    public FeedCachingService(PostDB postDB) {
        this.postDB = postDB;
    }

    public Set<Long> getTrendingPostIds(Set<String> tags, String location) {
//        try {
//            Set<Long> trendingPostIds = new HashSet<>();
//
//            redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
//                ValueOperations<String, Object> operations = redisTemplate.opsForValue();
//
//                // Pipeline the fetching of trending post IDs for each tag
//                tags.forEach(tag -> {
//                    String hashTagTrendingPostKey = HASHTAG_TRENDING_POST_PREFIX + redisKeyDelimiter + tag;
//                    String systemTagTrendingPostKey = SYSTEMTAG_TRENDING_POST_PREFIX + redisKeyDelimiter + tag;
//
//                    operations.get(hashTagTrendingPostKey);
//                    operations.get(systemTagTrendingPostKey);
//                });
//
//                // Pipeline the fetching of trending post IDs for the location
//                String locationTrendingPostKey = LOCATION_TRENDING_POST_PREFIX + redisKeyDelimiter + location;
//                operations.get(locationTrendingPostKey);
//
//                return null; // return type of RedisCallback
//            }).forEach(response -> {
//                if (response instanceof List) {
//                    trendingPostIds.addAll((List<Long>) response);
//                }
//            });
//
//            return trendingPostIds;
//        } catch (Exception e) {
//            throw new RuntimeException("Error while getting trending post ids from cache", e);
//        }

        return new HashSet<>(List.of(1L, 2L, 3L, 4L, 5L));
    }

    public List<Post> getTrendingPostInfo(Collection<Long> postIds) {
//        try {
//
//            List<Post> trendingPosts = new ArrayList<>();
//            redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
//                StringRedisConnection stringRedisConn = (StringRedisConnection) connection;
//                for (Long postId : postIds) {
//                    String trendingPostKey = TRENDING_POST_PREFIX + redisKeyDelimiter + postId;
//                    stringRedisConn.get(trendingPostKey);
//                }
//                return null;
//            }).forEach(response -> {
//                if (response instanceof List) {
//                    trendingPosts.addAll((List<Post>) response);
//                }
//            });
//
//            return trendingPosts;
//        } catch (Exception e) {
//            throw new RuntimeException("Error while getting trending post info from cache", e);
//        }

        ArrayList<Post> recoList = new ArrayList<>(postDB.getPosts());
        return recoList;

    }

    public List<Post> getTrendingPostsForUser(Set<String> tags, String location) {
        Set<Long> trendingPostIds = getTrendingPostIds(tags, location);
        return getTrendingPostInfo(trendingPostIds);
    }

}
