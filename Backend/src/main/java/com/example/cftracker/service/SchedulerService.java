package com.example.cftracker.service;

import com.example.cftracker.model.User;
import com.example.cftracker.repository.UserRepository;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SchedulerService {

    private final UserRepository repo;
    private final CodeforcesService cfService;

    public SchedulerService(UserRepository repo,
                            CodeforcesService cfService) {
        this.repo = repo;
        this.cfService = cfService;
    }

    @Scheduled(fixedRate = 86400000)
    public void updateAllUsers() {

        for (User user : repo.findAll()) {
            try {
                cfService.fetchAndSaveUser(user.getHandle());
            } catch (Exception e) {
                System.out.println("Failed for: " + user.getHandle());
            }
        }
    }
}