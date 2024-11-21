package com.example.abhijit.entities;

import lombok.Data;
import lombok.NonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
public class Post {
    private String id;
    private String post;
    @NonNull
    private Long createdTime;
    @NonNull
    private Long userId;
    private Set<Long> likedBy;
    private Set<Long> unlikedBy;
    private int score;

    public Post() {
        likedBy = new HashSet<>();
        unlikedBy = new HashSet<>();
        score = 0;
        this.id = UUID.randomUUID().toString();
        this.createdTime = System.currentTimeMillis();
    }
}
