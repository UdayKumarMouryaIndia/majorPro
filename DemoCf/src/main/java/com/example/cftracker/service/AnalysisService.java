package com.example.cftracker.service;

import com.example.cftracker.model.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AnalysisService {

    public int predictRating(List<Contest> contests) {

        if (contests == null || contests.size() < 2) return 0;

        int n = contests.size();
        double weighted = 0, total = 0;
        int weight = 1;

        for (int i = Math.max(1, n - 10); i < n; i++) {
            int delta = contests.get(i).getNewRating() - contests.get(i).getOldRating();
            weighted += delta * weight;
            total += weight;
            weight++;
        }

        double avg = weighted / total;
        int current = contests.get(n - 1).getNewRating();

        double variance = 0;
        for (int i = Math.max(1, n - 10); i < n; i++) {
            int delta = contests.get(i).getNewRating() - contests.get(i).getOldRating();
            variance += Math.pow(delta - avg, 2);
        }

        variance /= Math.min(10, n);

        return (int) (current + avg - Math.sqrt(variance) * 0.5);
    }

    public Map<String, Double> tagAccuracy(List<Submission> subs) {

        Map<String, Integer> attempts = new HashMap<>();
        Map<String, Integer> solved = new HashMap<>();

        for (Submission s : subs) {
            for (String tag : s.getTags()) {

                attempts.put(tag, attempts.getOrDefault(tag, 0) + 1);

                if ("OK".equals(s.getVerdict())) {
                    solved.put(tag, solved.getOrDefault(tag, 0) + 1);
                }
            }
        }

        Map<String, Double> res = new HashMap<>();

        for (String tag : attempts.keySet()) {
            res.put(tag, (double) solved.getOrDefault(tag, 0) / attempts.get(tag));
        }

        return res;
    }

    public Map<Integer, Double> difficulty(List<Submission> subs) {

        Map<Integer, Integer> attempts = new HashMap<>();
        Map<Integer, Integer> solved = new HashMap<>();

        for (Submission s : subs) {

            int r = s.getProblemRating();
            if (r == 0) continue;

            int bucket = (r / 100) * 100;

            attempts.put(bucket, attempts.getOrDefault(bucket, 0) + 1);

            if ("OK".equals(s.getVerdict())) {
                solved.put(bucket, solved.getOrDefault(bucket, 0) + 1);
            }
        }

        Map<Integer, Double> res = new HashMap<>();

        for (int b : attempts.keySet()) {
            res.put(b, (double) solved.getOrDefault(b, 0) / attempts.get(b));
        }

        return res;
    }

    public List<String> suggestions(Map<String, Double> acc) {

        List<String> list = new ArrayList<>();

        for (String tag : acc.keySet()) {
            double a = acc.get(tag);

            if (a < 0.4) list.add("Practice " + tag);
            else if (a < 0.7) list.add("Improve " + tag);
        }

        if (list.isEmpty()) {
            list.add("Try higher difficulty problems");
        }

        return list;
    }
}