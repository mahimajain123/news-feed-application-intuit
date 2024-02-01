package com.example.newsfeedapplication.repository;

import com.example.newsfeedapplication.entity.Post;
import com.example.newsfeedapplication.entity.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface PostCommentRepository
//        extends JpaRepository<PostComment, Long>
{
    List<PostComment> findByPost(Post post);
}