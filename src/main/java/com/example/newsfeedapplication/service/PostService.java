package com.example.newsfeedapplication.service;

import com.example.newsfeedapplication.entity.Post;
import com.example.newsfeedapplication.repository.PostRepository;
import com.example.newsfeedapplication.repository.mockDB.PostDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostDB postDB;

    @Autowired
    public PostService(PostDB postDB) {
        this.postDB = postDB;
    }

    public List<Post> getPostsByIds(List<Long> ids) {
        return postDB.findAllByIdIn(ids);
    }

    public List<Long> getPostIdByUserIds(List<Long> userIds) {
        return postDB.findPostIdByUserId(userIds);
    }
}