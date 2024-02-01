package com.example.newsfeedapplication.entity;

import com.example.newsfeedapplication.enums.ContentStatus;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "PostComment")
@Getter
@Setter
@NonNull
public class PostComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "comment_content")
    private String commentContent;

    @Column(name = "comment_status")
    private ContentStatus commentStatus;

    public PostComment() {
    }

    public PostComment(Long id, Post post, User user, String commentContent, ContentStatus commentStatus) {
        this.commentId = id;
        this.post = post;
        this.user = user;
        this.commentContent = commentContent;
        this.commentStatus = commentStatus; 
    }

}
