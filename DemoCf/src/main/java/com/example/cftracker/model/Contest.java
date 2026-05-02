package com.example.cftracker.model;

import lombok.Data;

@Data
public class Contest {
    private long contestId;
    private int rank;
    private int oldRating;
    private int newRating;
}