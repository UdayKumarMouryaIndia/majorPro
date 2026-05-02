package com.example.cftracker.dto;

public class AnalysisDTO {

    private double averageRank;
    private int totalContests;
    private int currentRating;

    public AnalysisDTO() {}

    public AnalysisDTO(double averageRank, int totalContests, int currentRating) {
        this.averageRank = averageRank;
        this.totalContests = totalContests;
        this.currentRating = currentRating;
    }

    public double getAverageRank() {
        return averageRank;
    }

    public void setAverageRank(double averageRank) {
        this.averageRank = averageRank;
    }

    public int getTotalContests() {
        return totalContests;
    }

    public void setTotalContests(int totalContests) {
        this.totalContests = totalContests;
    }

    public int getCurrentRating() {
        return currentRating;
    }

    public void setCurrentRating(int currentRating) {
        this.currentRating = currentRating;
    }
}