package com.example.newsfeedapplication.entity;

import com.example.newsfeedapplication.enums.EntityType;
import com.example.newsfeedapplication.enums.InteractionType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserInteraction {

    @JsonProperty("entity_type")
    private EntityType entityType;

    @JsonProperty("entity_id")
    private Long entityId;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("interaction_type")
    private InteractionType interactionType;

    @JsonProperty("interaction_data")
    private String interactionData;

    @JsonProperty("interaction_timestamp")
    private LocalDateTime interactionTimestamp;

    // Constructors
    public UserInteraction() {
    }

    public UserInteraction(EntityType entityType, Long entityId, Long userId, InteractionType interactionType, String interactionData, LocalDateTime interactionTimestamp) {
        this.entityType = entityType;
        this.entityId = entityId;
        this.userId = userId;
        this.interactionType = interactionType;
        this.interactionData = interactionData;
        this.interactionTimestamp = interactionTimestamp;
    }
}
