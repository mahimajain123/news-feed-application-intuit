# News Feed Application

This is a News Feed application built with Java, Spring Boot, and SQL. The application is designed to simulate a social media news feed, where users can post updates, comment on posts, and reply to comments.

The application is built around a RESTful architecture, with endpoints for creating and retrieving users, posts, comments, and replies. It uses Spring Boot for the backend, providing a robust and scalable framework for web application development. The data is stored in a SQL database, ensuring efficient and reliable data management.

The application also includes a mock database for testing purposes, simulating user interactions with the news feed. This includes creating posts, commenting on posts, and replying to comments. The mock database is populated with a variety of posts and comments to provide a realistic testing environment.

The News Feed Application is a great example of a modern web application, demonstrating the use of Java, Spring Boot, and SQL in a real-world scenario. It provides a solid foundation for further development and can be easily extended with additional features and functionality.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Java 8 or higher
- Maven
- SQL database

### Installing

1. Clone the repository
```bash
git clone https://github.com/mahima-jain_paytm/newsfeedapplication.git

```
### Usage
The application exposes the following endpoints:  

POST /v1/users: Create a new user

GET /v1/users/{id}: Retrieve a user by ID

PUT /v1/users/{id}: Update a user by ID

POST /v1/post-comments: Add a new comment to a post

GET /v1/post-comments/post/{postId}: Get comments by post ID

POST /v1/comment-replies: Add a reply to a comment

GET /v1/comment-replies/{commentId}: Get replies by comment ID