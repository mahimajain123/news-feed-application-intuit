package com.example.newsfeedapplication.repository.mockDB;

import com.example.newsfeedapplication.enums.ContentStatus;
import com.example.newsfeedapplication.entity.Post;
import com.example.newsfeedapplication.entity.PostComment;
import com.example.newsfeedapplication.entity.User;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@DependsOn({"postDB", "userDB"})
public class PostCommentDB {

    @Autowired
    private PostDB postDB;

    @Autowired
    private UserDB userDB;

    @Getter
    private List<PostComment> postComments;

    @Autowired
    public PostCommentDB(UserDB userDB, PostDB postDB) {
        this.userDB = userDB;
        this.postDB = postDB;

        postComments = new ArrayList<>();
        createMockEntries(10);
    }


    public PostComment save(PostComment postComment) {
        postComments.add(postComment);
        return postComment;
    }

    public void createMockEntries(int numberOfEntries) {
        if (userDB.getUsers().size() < numberOfEntries || postDB.getPostsSize() < numberOfEntries) {
            throw new IllegalStateException("Not enough users or posts to create comments");
        }

        String[] mockComments = {
                "Great post!",
                "I totally agree with you.",
                "This is very insightful.",
                "Thanks for sharing this.",
                "I have a different perspective on this.",
                "This is very helpful, thank you.",
                "I learned something new today.",
                "This is a very interesting read.",
                "I'm looking forward to your next post.",
                "Keep up the good work!"
        };

        for (int i = 0; i < numberOfEntries; i++) {
            User user = userDB.getUsers().get(i);
            Post post = postDB.getPosts().get(numberOfEntries-i-1);

            postComments.add(new PostComment(i+1L, post, user, mockComments[i], ContentStatus.PUBLISHED));
        }
    }

    public List<PostComment> findByPost(Post post) {
        List<PostComment> result = new ArrayList<>();
        for (PostComment postComment : postComments) {
            if (postComment.getPost().getPostId().equals(post.getPostId())) {
                result.add(postComment);
            }
        }
        return result;
    }
}