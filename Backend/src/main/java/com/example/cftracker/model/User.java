package com.example.cftracker.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@Document(collection = "users")
public class User {
    @Id
    private String handle;
    private int rating;
    private List<Contest> contests;
    private List<Submission> submissions;
    private long lastUpdated;
}