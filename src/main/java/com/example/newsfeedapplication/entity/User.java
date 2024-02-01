package com.example.newsfeedapplication.entity;

import javax.persistence.*;

import com.example.newsfeedapplication.enums.UserPrivacyStatus;
import com.example.newsfeedapplication.enums.VerifiedStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name = "User")
@Getter
@Setter
@AllArgsConstructor
@NonNull
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username")
    private String username;

    @Column(name = "name")
    private String name;

    @Column(name = "profile_photo")
    private String profilePhoto;

    @Column(name = "user_about")
    private String userAbout;

    @Column(name = "verified_status")
    private VerifiedStatus verifiedStatus;

    @Column(name = "privacy_status")
    private UserPrivacyStatus privacyStatus;

    // Constructors
    public User() {
    }
    public User(String username, String name, String profilePhoto, String userAbout, VerifiedStatus verifiedStatus, UserPrivacyStatus privacyStatus) {
        this.username = username;
        this.name = name;
        this.profilePhoto = profilePhoto;
        this.userAbout = userAbout;
        this.verifiedStatus = verifiedStatus;
        this.privacyStatus = privacyStatus;
    }
}

