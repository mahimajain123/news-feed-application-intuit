package com.example.newsfeedapplication.repository.mockDB;

import com.example.newsfeedapplication.enums.ContentStatus;
import com.example.newsfeedapplication.enums.PostType;
import com.example.newsfeedapplication.entity.Post;
import com.example.newsfeedapplication.entity.User;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@DependsOn("userDB")
public class PostDB {

    @Getter
    private List<Post> posts;

    @Autowired
    public PostDB(UserDB userDB) {

        posts = new ArrayList<>();
        List<User> users = userDB.getUsers();

        posts.add(new Post(1L, users.get(0), "Excited to start my new journey at DreamsLand!", PostType.TEXT, ContentStatus.PUBLISHED));
        posts.add(new Post(2L, users.get(1), "Just landed on Mars. The view is breathtaking!", PostType.TEXT, ContentStatus.PUBLISHED));
        posts.add(new Post(3L, users.get(2), "Launching a new product at Amazon today. Stay tuned!", PostType.TEXT, ContentStatus.PUBLISHED));
        posts.add(new Post(4L, users.get(3), "My new book 'Limitless Imaginations and Possibilities' is now available on Amazon!", PostType.TEXT, ContentStatus.PUBLISHED));
        posts.add(new Post(5L, users.get(4), "Honored to receive the Nobel Peace Prize this year.", PostType.TEXT, ContentStatus.PUBLISHED));
        posts.add(new Post(6L, users.get(5), "Join me for a live session on climate change.", PostType.TEXT, ContentStatus.PUBLISHED));
        posts.add(new Post(7L, users.get(6), "Excited to announce the launch of SpaceX's new spacecraft!", PostType.TEXT, ContentStatus.PUBLISHED));
        posts.add(new Post(8L, users.get(7), "Apple's new product launch event is happening tomorrow. Don't miss it!", PostType.TEXT, ContentStatus.PUBLISHED));
        posts.add(new Post(9L, users.get(8), "Introducing new privacy features on Facebook.", PostType.TEXT, ContentStatus.PUBLISHED));
        posts.add(new Post(10L, users.get(9), "Amazon's Prime Day Sale starts tomorrow. Happy shopping!", PostType.TEXT, ContentStatus.PUBLISHED));
        posts.add(new Post(11L, users.get(1), "That's one small step for man, one giant leap for mankind.", PostType.TEXT, ContentStatus.PUBLISHED));
    }

    public List<Post> getPostByUserId(Long userId) {
        return posts.stream().filter(
                post -> post.getUserId().equals(userId)
        ).toList();
    }

    public Post getPostByPostId(Long postId) {
        return posts.stream().filter(
                post -> post.getPostId().equals(postId)
        ).toList().get(0);
    }

    public List<Post> getPosts() {
        return posts;
    }

    public int getPostsSize() {
        return posts.size();
    }

    public List<Post> findAllByIdIn(List<Long> ids) {
        return posts.stream().filter(
                post -> ids.contains(post.getPostId())
        ).toList();
    }

    public List<Long> findPostIdByUserId(List<Long> userIds) {
        return posts.stream().filter(
                post -> userIds.contains(post.getUserId().getUserId())
        ).map(Post::getPostId).toList();
    }
}
