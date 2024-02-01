package com.example.newsfeedapplication.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "UserInterests")
@Getter
@Setter
@NonNull
public class UserInterests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "interests")
    private List<String> interests;

    @Column(name = "is_enabled")
    private Boolean isEnabled;

    // Constructors
    public UserInterests() {
    }

    public UserInterests(User user, List<String> interests, Boolean isEnabled) {
        this.user = user;
        this.interests = interests;
        this.isEnabled = isEnabled;
    }

    public UserInterests(Long id, User user, List<String> interests, Boolean isEnabled) {
        this.id = id;
        this.user = user;
        this.interests = interests;
        this.isEnabled = isEnabled;
    }
}