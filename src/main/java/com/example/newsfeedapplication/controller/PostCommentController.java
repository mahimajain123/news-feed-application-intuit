package com.example.newsfeedapplication.controller;

import com.example.newsfeedapplication.entity.Post;
import com.example.newsfeedapplication.entity.PostComment;
import com.example.newsfeedapplication.repository.mockDB.PostCommentDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/post-comments")
public class PostCommentController {

    private final PostCommentDB postCommentDB;

    @Autowired
    public PostCommentController(PostCommentDB postCommentDB) {
        this.postCommentDB = postCommentDB;
    }

    @PostMapping
    public PostComment addComment(@RequestBody PostComment postComment) {
        return postCommentDB.save(postComment);
    }

    @GetMapping("/post/{postId}")
    public List<PostComment> getCommentsByPostId(@PathVariable Long postId) {
        Post post = new Post();
        post.setPostId(postId);
        return postCommentDB.findByPost(post);
    }
}