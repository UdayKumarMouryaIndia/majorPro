package com.example.cftracker.controller;

import com.example.cftracker.model.User;
import com.example.cftracker.repository.UserRepository;
import com.example.cftracker.service.AnalysisService;
import com.example.cftracker.service.CodeforcesService;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {

    private final CodeforcesService cfService;
    private final AnalysisService analysisService;
    private final UserRepository repo;

    public UserController(CodeforcesService cfService,
                          AnalysisService analysisService,
                          UserRepository repo) {
        this.cfService = cfService;
        this.analysisService = analysisService;
        this.repo = repo;
    }

    // Refresh user data
    @GetMapping("/{handle}/refresh")
    public User refresh(@PathVariable String handle) {
        return cfService.fetchAndSaveUser(handle);
    }

    // Get stored user
    @GetMapping("/{handle}")
    public User getUser(@PathVariable String handle) {
        return repo.findById(handle)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Prediction
    @GetMapping("/{handle}/prediction")
    public Map<String, Object> prediction(@PathVariable String handle) {

        User user = repo.findById(handle)
                .orElseThrow(() -> new RuntimeException("User not found"));

        int predicted = analysisService.predictRating(user.getContests());

        Map<String, Object> res = new HashMap<>();
        res.put("currentRating", user.getRating());
        res.put("predictedRating", predicted);

        return res;
    }

    // Tag Analysis
    @GetMapping("/{handle}/tags")
    public Map<String, Object> tags(@PathVariable String handle) {

        User user = repo.findById(handle)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Map<String, Double> accuracy =
                analysisService.tagAccuracy(user.getSubmissions());

        Map<String, Object> res = new HashMap<>();
        res.put("accuracy", accuracy);
        res.put("suggestions", analysisService.suggestions(accuracy));

        return res;
    }

    // Difficulty Analysis
    @GetMapping("/{handle}/difficulty")
    public Map<Integer, Double> difficulty(@PathVariable String handle) {

        User user = repo.findById(handle)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return analysisService.difficulty(user.getSubmissions());
    }
}