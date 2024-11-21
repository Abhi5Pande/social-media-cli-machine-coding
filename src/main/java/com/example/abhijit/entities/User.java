package com.example.abhijit.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
public class User {
    private Long id;
    private String userName;
    private Set<Long> followingIds;

    public User() {
        this.followingIds = new HashSet<>();
    }
}
