package com.example.cftracker.dto;

public class PredictionDTO {

    private int currentRating;
    private int predictedRating;
    private int minRange;
    private int maxRange;

    public PredictionDTO() {}

    public PredictionDTO(int currentRating, int predictedRating, int minRange, int maxRange) {
        this.currentRating = currentRating;
        this.predictedRating = predictedRating;
        this.minRange = minRange;
        this.maxRange = maxRange;
    }

    public int getCurrentRating() {
        return currentRating;
    }

    public void setCurrentRating(int currentRating) {
        this.currentRating = currentRating;
    }

    public int getPredictedRating() {
        return predictedRating;
    }

    public void setPredictedRating(int predictedRating) {
        this.predictedRating = predictedRating;
    }

    public int getMinRange() {
        return minRange;
    }

    public void setMinRange(int minRange) {
        this.minRange = minRange;
    }

    public int getMaxRange() {
        return maxRange;
    }

    public void setMaxRange(int maxRange) {
        this.maxRange = maxRange;
    }
}