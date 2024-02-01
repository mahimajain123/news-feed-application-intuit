package com.example.newsfeedapplication;

import com.example.newsfeedapplication.dto.FeedRequest;
import com.example.newsfeedapplication.entity.Post;
import com.example.newsfeedapplication.entity.User;
import com.example.newsfeedapplication.repository.mockDB.UserFollowRelationshipDB;
import com.example.newsfeedapplication.repository.mockDB.PostDB;
import com.example.newsfeedapplication.repository.mockDB.UserDB;
import com.example.newsfeedapplication.service.FeedCachingService;
import com.example.newsfeedapplication.service.FeedService;
import com.example.newsfeedapplication.service.PostMetadataService;
import com.example.newsfeedapplication.service.PostService;
import com.example.newsfeedapplication.service.UserInterestsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.anySet;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FeedServiceTest {

    @InjectMocks
    private FeedService feedService;

    @Mock
    private PostMetadataService postMetadataService;

    @Mock
    private UserFollowRelationshipDB followRepositoryDB;

    @Mock
    private PostDB postDB;

    @Mock
    private UserDB userDB;

    @Mock
    private UserInterestsService userInterestsService;

    @Mock
    private FeedCachingService feedCachingService;

    @Mock
    private PostService postService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getFeed_returnsPostsFromCache_whenCacheIsNotEmpty() {
        FeedRequest feedRequest = new FeedRequest(1L, "location", Arrays.asList("tag1", "tag2"));
        List<Post> cachedPosts = Arrays.asList(new Post(), new Post());
        when(feedCachingService.getTrendingPostsForUser(anySet(), anyString())).thenReturn(cachedPosts);

        List<Post> result = feedService.getFeed(feedRequest);

        assertEquals(cachedPosts, result);
        verify(postMetadataService, never()).getUserFeedPostIdsFromES(anyList(), anyList(), anyString(), anyLong());
        verify(postService, never()).getPostIdByUserIds(anyList());
        verify(postService, never()).getPostsByIds(anyList());
    }

    @Test
    public void getFeed_returnsPostsFromDatabase_whenCacheIsEmpty() {
        FeedRequest feedRequest = new FeedRequest(1L, "location", Arrays.asList("tag1", "tag2"));
        List<Long> postIdsFromES = Arrays.asList(1L, 2L);
        List<Long> postIdsFromDB = Arrays.asList(3L, 4L);
        List<Post> postsFromDB = Arrays.asList(new Post(), new Post());
        when(feedCachingService.getTrendingPostsForUser(anySet(), anyString())).thenReturn(null);
        when(postMetadataService.getUserFeedPostIdsFromES(anyList(), anyList(), anyString(), anyLong())).thenReturn(postIdsFromES);
        when(followRepositoryDB.getFollowers(anyLong())).thenReturn(Arrays.asList(new User(), new User()));
        when(postService.getPostIdByUserIds(anyList())).thenReturn(postIdsFromDB);
        when(postService.getPostsByIds(anyList())).thenReturn(postsFromDB);

        List<Post> result = feedService.getFeed(feedRequest);

        assertEquals(postsFromDB, result);
    }
}
