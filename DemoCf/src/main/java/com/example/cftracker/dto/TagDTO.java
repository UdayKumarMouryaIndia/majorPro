package com.example.cftracker.dto;

import java.util.List;
import java.util.Map;

public class TagDTO {

    private Map<String, Double> accuracy;
    private List<String> weakTopics;
    private List<String> suggestions;

    public TagDTO() {}

    public TagDTO(Map<String, Double> accuracy,
                  List<String> weakTopics,
                  List<String> suggestions) {
        this.accuracy = accuracy;
        this.weakTopics = weakTopics;
        this.suggestions = suggestions;
    }

    public Map<String, Double> getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Map<String, Double> accuracy) {
        this.accuracy = accuracy;
    }

    public List<String> getWeakTopics() {
        return weakTopics;
    }

    public void setWeakTopics(List<String> weakTopics) {
        this.weakTopics = weakTopics;
    }

    public List<String> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<String> suggestions) {
        this.suggestions = suggestions;
    }
}