package com.example.newsfeedapplication.service;

import com.example.newsfeedapplication.entity.UserInteraction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * this service works as Kafka producer to produce all user activity to Kafka topic and
 * a separate microservice consumes the activity from Kafka topic and update the ES
 * that microservice will be responsible for analytics and
 * is not implemented in this project
 **/
@Service
public class KafkaService {
    private Queue<UserInteraction> userInteractionQueue;

    @Autowired
    UserInteractionService userInteractionService;

    KafkaService() {
        this.userInteractionQueue = new ArrayDeque<>();
    }

    public void pushUserInteraction(UserInteraction userInteraction) {
        this.userInteractionQueue.add(userInteraction);
    }

}
