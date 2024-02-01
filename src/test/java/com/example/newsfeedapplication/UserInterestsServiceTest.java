package com.example.newsfeedapplication;

import com.example.newsfeedapplication.entity.UserInterests;
import com.example.newsfeedapplication.repository.mockDB.UserInterestsDB;
import com.example.newsfeedapplication.service.UserInterestsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UserInterestsServiceTest {

    @Mock
    private UserInterestsDB userInterestsDB;

    @InjectMocks
    private UserInterestsService userInterestsService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getUserInterests_returnsUserInterests_whenUserExists() {
        Long userId = 1L;
        UserInterests userInterests = new UserInterests();
        userInterests.setInterests(Arrays.asList("Sports", "Technology"));
        when(userInterestsDB.findByUserId(userId)).thenReturn(userInterests);

        List<String> result = userInterestsService.getUserInterests(userId);

        assertEquals(userInterests.getInterests(), result);
    }

    @Test
    public void getUserInterests_returnsEmptyList_whenUserDoesNotExist() {
        Long userId = 2L;
        when(userInterestsDB.findByUserId(userId)).thenReturn(null);

        List<String> result = userInterestsService.getUserInterests(userId);

        assertEquals(0, result.size());
    }
}
