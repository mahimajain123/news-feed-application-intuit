package com.example.newsfeedapplication.controller;


import com.example.newsfeedapplication.entity.UserInteraction;
import com.example.newsfeedapplication.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/activity")
public class ActivityController {

    @Autowired
    private KafkaService kafkaService;
    @PostMapping
    public HttpStatus createUserInteraction(@RequestBody List<UserInteraction> userInteraction) {
        new Thread(() -> userInteraction
                .forEach(u -> kafkaService.pushUserInteraction(u))
        )
                .start();
        return HttpStatus.OK;
    }

}
