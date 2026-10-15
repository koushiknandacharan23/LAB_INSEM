package com.example.videoservice;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/videos")
public class VideoController {
    @GetMapping("/all")
    public List<Map<String, String>> getVideos() {
        return List.of(
            Map.of("id", "101", "title", "Introduction to Streaming"),
            Map.of("id", "102", "title", "Java Microservices Basics"),
            Map.of("id", "103", "title", "Spring Cloud in Practice")
        );
    }

    @GetMapping("/{id}")
    public Map<String, String> getVideo(@PathVariable String id) {
        return Map.of("id", id, "title", "Demo Video " + id,
                      "message", "Video details returned by video-service");
    }
}
