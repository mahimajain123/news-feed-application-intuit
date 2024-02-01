CREATE TABLE CommentReply (
    reply_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    comment_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    reply_content TEXT NOT NULL,
    reply_status ENUM('PUBLISHED', 'DRAFT', 'DELETED') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (comment_id) REFERENCES PostComment(comment_id),
    FOREIGN KEY (user_id) REFERENCES User(user_id),
    INDEX comment_id_index (comment_id),
    INDEX user_id_index (user_id),
    INDEX reply_status_index (reply_status)
);

CREATE TABLE UserFollowRelationship (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    follower_user_id BIGINT NOT NULL,
    followed_user_id BIGINT NOT NULL,
    following_status ENUM('FOLLOWING', 'NOT_FOLLOWING') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (follower_user_id) REFERENCES User(user_id),
    FOREIGN KEY (followed_user_id) REFERENCES User(user_id),
    INDEX follower_user_id_index (follower_user_id),
    INDEX followed_user_id_index (followed_user_id)
);

CREATE TABLE Post (
    post_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    post_content TEXT NOT NULL,
    post_type ENUM('TEXT', 'IMAGE', 'VIDEO') NOT NULL,
    post_status ENUM('PUBLISHED', 'DRAFT', 'DELETED') NOT NULL,
    user_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES User(user_id),
    INDEX user_id_index (user_id),
    INDEX post_type_index (post_type),
    INDEX post_status_index (post_status)
);

CREATE TABLE PostComment (
    comment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    post_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    comment_content TEXT NOT NULL,
    comment_status ENUM('PUBLISHED', 'DRAFT', 'DELETED') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (post_id) REFERENCES Post(post_id),
    FOREIGN KEY (user_id) REFERENCES User(user_id),
    INDEX post_id_index (post_id),
    INDEX user_id_index (user_id),
    INDEX comment_status_index (comment_status)
);

CREATE TABLE UserInterests (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    interests TEXT NOT NULL,
    is_enabled BOOLEAN NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES User(user_id),
    INDEX user_id_index (user_id)
);

CREATE TABLE User (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    profile_photo VARCHAR(255) NOT NULL,
    user_about VARCHAR(255) NOT NULL,
    verified_status ENUM('VERIFIED', 'UNVERIFIED') NOT NULL,
    privacy_status ENUM('PRIVATE', 'SEMI_PRIVATE', 'PUBLIC') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL
);
