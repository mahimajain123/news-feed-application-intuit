package com.example.newsfeedapplication.entity;

import com.example.newsfeedapplication.enums.ContentStatus;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CommentReply")
@Getter
@Setter
@NonNull
public class CommentReply implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private Long replyId;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private PostComment comment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "reply_content", length = 512)
    private String replyContent;

    @Column(name = "reply_status")
    private ContentStatus replyStatus;

    // Constructors
    public CommentReply() {
    }

    public CommentReply(PostComment comment, User user, String replyContent, ContentStatus replyStatus) {
        this.comment = comment;
        this.user = user;
        this.replyContent = replyContent;
        this.replyStatus = replyStatus;
    }

}
