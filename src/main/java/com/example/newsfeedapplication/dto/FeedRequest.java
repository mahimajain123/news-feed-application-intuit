package com.example.newsfeedapplication.dto;

import com.example.newsfeedapplication.utility.Coordinates;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class FeedRequest {

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("location")
    private String location;

    @JsonProperty("system_tags")
    private List<String> systemTags;

}
