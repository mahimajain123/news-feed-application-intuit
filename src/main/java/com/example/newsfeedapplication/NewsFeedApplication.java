package com.example.newsfeedapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("com.example.newsfeedapplication.entity")
@ComponentScan(basePackages = {"com.example.newsfeedapplication.controller", "com.example.newsfeedapplication.service", "com.example.newsfeedapplication.repository", "com.example.newsfeedapplication.config", "com.example.newsfeedapplication.entity", "com.example.newsfeedapplication.enums", "com.example.newsfeedapplication.kafka", "com.example.newsfeedapplication.model", "com.example.newsfeedapplication.security"})
public class NewsFeedApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsFeedApplication.class, args);
	}

}
