package com.example.newsfeedapplication.repository.mockDB;

import com.example.newsfeedapplication.enums.UserPrivacyStatus;
import com.example.newsfeedapplication.enums.VerifiedStatus;
import com.example.newsfeedapplication.entity.User;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UserDB {
    private List<User> users;

    public UserDB() {
        users = Arrays.asList(
                new User(1L, "john_doe", "John Doe", "https://example.com/john_doe.jpg", "Software Engineer | Dreamer", VerifiedStatus.VERIFIED, UserPrivacyStatus.PUBLIC),
                new User(2L, "jane_doe", "Jane Doe", "https://example.com/jane_doe.jpg", "First Traveller to Mars", VerifiedStatus.VERIFIED, UserPrivacyStatus.PRIVATE),
                new User(3L, "mike_smith", "Mike Smith", "https://example.com/mike_smith.jpg", "President Of Happy Club, United Nations", VerifiedStatus.UNVERIFIED, UserPrivacyStatus.SEMI_PRIVATE),
                new User(4L, "sarah_jones", "Sarah Jones", "https://example.com/sarah_jones.jpg", "Author: Limitless Imaginations and Possibilities", VerifiedStatus.UNVERIFIED, UserPrivacyStatus.PUBLIC),
                new User(5L, "paul_brown", "Paul Brown", "https://example.com/paul_brown.jpg", "Nobel Peace Prize Laureate", VerifiedStatus.UNVERIFIED, UserPrivacyStatus.PRIVATE),
                new User(6L, "emma_watson", "Emma Watson", "https://example.com/emma_watson.jpg", "Actress and Activist", VerifiedStatus.VERIFIED, UserPrivacyStatus.PUBLIC),
                new User(7L, "elon_musk", "Elon Musk", "https://example.com/elon_musk.jpg", "CEO of SpaceX and Tesla", VerifiedStatus.VERIFIED, UserPrivacyStatus.PUBLIC),
                new User(8L, "tim_cook", "Tim Cook", "https://example.com/tim_cook.jpg", "CEO of Apple", VerifiedStatus.UNVERIFIED, UserPrivacyStatus.PUBLIC),
                new User(9L, "mark_zuckerberg", "Mark Zuckerberg", "https://example.com/mark_zuckerberg.jpg", "CEO of Facebook", VerifiedStatus.VERIFIED, UserPrivacyStatus.PRIVATE),
                new User(10L, "jeff_bezos", "Jeff Bezos", "https://example.com/jeff_bezos.jpg", "Founder of Amazon", VerifiedStatus.UNVERIFIED, UserPrivacyStatus.PUBLIC)
        );
    }

    public List<User> getUsers() {
        return users;
    }

    public User getUserByUserId(Long id) {
        for (User user : users) {
            if (Objects.equals(user.getUserId(), id)) {
                return user;
            }
        }
        return null;
    }

    public User getUserByUsername(String username) {
        for (User user : users) {
            if (Objects.equals(user.getUsername(), username)) {
                return user;
            }
        }
        return null;
    }

    public User findById(Long userId) {
        return users.stream().filter(user -> user.getUserId().equals(userId)).findFirst().orElse(null);
    }

    public User save(User user) {
        users.add(user);
        return user;
    }

    public User update(User user) {

        User userToUpdate = findById(user.getUserId());
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setName(user.getName());
        userToUpdate.setProfilePhoto(user.getProfilePhoto());
        userToUpdate.setUserAbout(user.getUserAbout());
        userToUpdate.setVerifiedStatus(user.getVerifiedStatus());
        userToUpdate.setPrivacyStatus(user.getPrivacyStatus());

        users.replaceAll(u -> u.getUserId().equals(user.getUserId()) ? userToUpdate : u);
        return userToUpdate;
    }
}
