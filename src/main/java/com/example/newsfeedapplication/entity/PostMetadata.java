package com.example.newsfeedapplication.entity;

import com.example.newsfeedapplication.utility.Coordinates;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostMetadata {

    @JsonProperty("post_id")
    private Long postId;

    @JsonProperty("hashtags")
    private List<String> hashtags;

    @JsonProperty("system_tags")
    private List<String> systemTags;

    @JsonProperty("location")
    private Coordinates location;

    @JsonProperty("user_id")
    private Long userId;

    // Constructors
    public PostMetadata() {
    }

    public PostMetadata(Long postId, List<String> hashtags, List<String> systemTags, Coordinates location, Long userId) {
        this.postId = postId;
        this.hashtags = hashtags;
        this.systemTags = systemTags;
        this.location = location;
        this.userId = userId;
    }
}