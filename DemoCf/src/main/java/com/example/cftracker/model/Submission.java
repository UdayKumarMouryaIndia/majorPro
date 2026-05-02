package com.example.cftracker.model;

import lombok.Data;
import java.util.List;

@Data
public class Submission {
    private String verdict;
    private int problemRating;
    private List<String> tags;
}
