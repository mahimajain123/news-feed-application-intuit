package com.example.newsfeedapplication.controller;

import com.example.newsfeedapplication.entity.CommentReply;
import com.example.newsfeedapplication.entity.PostComment;
import com.example.newsfeedapplication.repository.mockDB.CommentReplyDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/comment-replies")
public class CommentReplyController {

    @Autowired
    private final CommentReplyDB commentReplyDB;

    public CommentReplyController(CommentReplyDB commentReplyDB) {
        this.commentReplyDB = commentReplyDB;
    }

    @PostMapping
    public CommentReply addReply(@RequestBody CommentReply commentReply) {
        return commentReplyDB.save(commentReply);
    }

    @GetMapping("/{commentId}")
    public List<CommentReply> getRepliesByCommentId(@PathVariable Long commentId) {
        PostComment comment = new PostComment();
        comment.setCommentId(commentId);
        return commentReplyDB.findByComment(comment);
    }
}