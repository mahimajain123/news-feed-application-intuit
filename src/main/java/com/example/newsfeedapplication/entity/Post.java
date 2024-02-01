package com.example.newsfeedapplication.entity;

import com.example.newsfeedapplication.enums.ContentStatus;
import com.example.newsfeedapplication.enums.PostType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Post")
@Getter
@Setter
@NonNull
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @Column(name = "post_content", length = 512, nullable = false)
    private String postContent;

    @Column(name = "post_type", nullable = false)
    private PostType postType;

    @Column(name = "post_status", nullable = false)
    private ContentStatus postStatus;

    // Constructors
    public Post() {
    }

    public Post(User userId, String postContent, PostType postType, ContentStatus postStatus) {
        this.userId = userId;
        this.postContent = postContent;
        this.postType = postType;
        this.postStatus = postStatus;
    }

}
