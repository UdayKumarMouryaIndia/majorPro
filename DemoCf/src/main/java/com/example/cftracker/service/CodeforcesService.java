package com.example.cftracker.service;

import com.example.cftracker.model.*;
import com.example.cftracker.repository.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class CodeforcesService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper mapper = new ObjectMapper();
    private final UserRepository repo;

    public CodeforcesService(UserRepository repo) {
        this.repo = repo;
    }

    public User fetchAndSaveUser(String handle) {

        User existing = repo.findById(handle).orElse(null);

        long now = System.currentTimeMillis();
        long CACHE_TIME = 6 * 60 * 60 * 1000;

        if (existing != null && (now - existing.getLastUpdated()) < CACHE_TIME) {
            return existing;
        }

        try {
            // Contest Data
            String ratingUrl = "https://codeforces.com/api/user.rating?handle=" + handle;
            JsonNode ratingRes = mapper.readTree(
                    restTemplate.getForObject(ratingUrl, String.class));

            List<Contest> contests = new ArrayList<>();

            for (JsonNode node : ratingRes.get("result")) {
                Contest c = new Contest();
                c.setContestId(node.get("contestId").asLong());
                c.setRank(node.get("rank").asInt());
                c.setOldRating(node.get("oldRating").asInt());
                c.setNewRating(node.get("newRating").asInt());
                contests.add(c);
            }

            // Submission Data
            String subUrl = "https://codeforces.com/api/user.status?handle=" + handle;
            JsonNode subRes = mapper.readTree(
                    restTemplate.getForObject(subUrl, String.class));

            List<Submission> submissions = new ArrayList<>();

            for (JsonNode node : subRes.get("result")) {

                Submission s = new Submission();

                s.setVerdict(node.get("verdict") != null
                        ? node.get("verdict").asText()
                        : "UNKNOWN");

                JsonNode problem = node.get("problem");

                if (problem.get("rating") != null)
                    s.setProblemRating(problem.get("rating").asInt());

                List<String> tags = new ArrayList<>();
                for (JsonNode t : problem.get("tags")) {
                    tags.add(t.asText());
                }

                s.setTags(tags);
                submissions.add(s);
            }

            User user = new User();
            user.setHandle(handle);
            user.setContests(contests);
            user.setSubmissions(submissions);
            user.setLastUpdated(now);

            if (!contests.isEmpty()) {
                user.setRating(contests.get(contests.size() - 1).getNewRating());
            }

            return repo.save(user);

        } catch (Exception e) {
            throw new RuntimeException("Error fetching Codeforces data");
        }
    }
}