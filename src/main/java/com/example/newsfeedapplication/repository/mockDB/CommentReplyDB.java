package com.example.newsfeedapplication.repository.mockDB;

import com.example.newsfeedapplication.enums.ContentStatus;
import com.example.newsfeedapplication.entity.CommentReply;
import com.example.newsfeedapplication.entity.PostComment;
import com.example.newsfeedapplication.entity.User;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@DependsOn("postCommentDB")
public class CommentReplyDB {
    private PostCommentDB postCommentDB;
    private UserDB userDB;

    @Autowired
    public CommentReplyDB(UserDB userDB, PostCommentDB postCommentDB) {
        this.userDB = userDB;
        this.postCommentDB = postCommentDB;
    }

    @Getter
    private List<CommentReply> commentReplies;

    public List<CommentReply> findByComment(PostComment comment) {
        List<CommentReply> replies = new ArrayList<>();
        for (CommentReply reply : commentReplies) {
            if (reply.getComment().getCommentId().equals(comment.getCommentId())) {
                replies.add(reply);
            }
        }
        return replies;
    }

    public CommentReply save(CommentReply commentReply) {
        commentReplies.add(commentReply);
        return commentReply;
    }

    public CommentReplyDB() {
        commentReplies = new ArrayList<>();
        PostComment postComment = postCommentDB.getPostComments().get(0);
        User user = userDB.getUsers().get(4);
        commentReplies.add(new CommentReply(postComment, user, "Nice weather mate!", ContentStatus.PUBLISHED));
    }
}