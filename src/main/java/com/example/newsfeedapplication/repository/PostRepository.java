package com.example.newsfeedapplication.repository;

import com.example.newsfeedapplication.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface PostRepository
//        extends JpaRepository<Post, Long>
{
    List<Post> findAllByIdIn(List<Long> ids);

    List<Long> findPostIdByUserId(List<Long> userId);
}