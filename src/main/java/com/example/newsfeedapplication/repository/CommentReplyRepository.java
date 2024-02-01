package com.example.newsfeedapplication.repository;

import com.example.newsfeedapplication.entity.CommentReply;
import com.example.newsfeedapplication.entity.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface CommentReplyRepository
//        extends JpaRepository<CommentReply, Long>
{
    List<CommentReply> findByComment(PostComment comment);
}