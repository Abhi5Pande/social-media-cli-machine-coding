package com.example.abhijit.db;

import com.example.abhijit.entities.Post;
import com.example.abhijit.entities.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class Storage {
    private final Map<Long, User> usersTable;
    private final Map<String, Post> postsTable;

    public Map<Long, User> getUsersTable() {
        return usersTable;
    }

    public Map<String, Post> getPostsTable() {
        return postsTable;
    }

    public Storage() {
        usersTable = new HashMap<>();
        postsTable = new HashMap<>();
    }
}
