package com.example.newsfeedapplication.repository;

import com.example.newsfeedapplication.entity.UserInterests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface UserInterestsRepository
//        extends JpaRepository<UserInterests, Long>
{
    UserInterests getByUserId(Long userId);
}