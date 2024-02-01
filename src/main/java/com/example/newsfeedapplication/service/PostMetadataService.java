package com.example.newsfeedapplication.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostMetadataService {

    public List<Long> getPostIdsByHashtag(List<String> hashtag) {
        return new ArrayList<>(){
            {
                add(1L);
                add(3L);
                add(5L);
            }
        };
    }

    public List<Long> getPostIdsBySystemTag(List<String> systemTag) {
        return new ArrayList<>(){
            {
                add(2L);
                add(4L);
                add(6L);
            }
        };
    }
    public List<Long> getPostIdsByLocation(String location) {
        return new ArrayList<>(){
            {
                add(7L);
                add(8L);
                add(9L);
            }
        };
    }

    public List<Long> getPostIdsAlreadyViewedByUser(Long userID) {
        return new ArrayList<>(){
            {
                add(2L);
                add(5L);
                add(9L);
            }
        };
    }

    public List<Long> getUserFeedPostIdsFromES(
            List<String> hashtag, List<String> systemTag, String location, Long userID){
        List<Long> combinedPostIds = new ArrayList<>();
        combinedPostIds.addAll(getPostIdsByHashtag(hashtag));
        combinedPostIds.addAll(getPostIdsBySystemTag(systemTag));
        combinedPostIds.addAll(getPostIdsByLocation(location));
        combinedPostIds.removeAll(getPostIdsAlreadyViewedByUser(userID));

        return combinedPostIds;
    }
}
