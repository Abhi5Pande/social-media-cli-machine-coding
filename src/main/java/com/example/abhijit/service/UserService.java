package com.example.abhijit.service;

import com.example.abhijit.db.Storage;
import com.example.abhijit.entities.Post;
import com.example.abhijit.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

import static io.micrometer.common.util.StringUtils.isEmpty;

@Service
public class UserService {

    private final Storage storage;

    @Autowired
    public UserService(Storage storage) {
        this.storage = storage;
    }

    public void handleRegisterUser(Long userId, String userName) {
        if(storage.getUsersTable().containsKey(userId)) {
            System.out.println("This userId already exists");
        }
        else {
            User user = new User();
            user.setId(userId);
            user.setUserName(userName);
            storage.getUsersTable().put(userId, user);
            System.out.printf("%s Registered\n",userName);
        }
    }

    public void handleUserInteraction(Long followeeId, Long followerId, String action) {
        if( Objects.equals(followeeId,followerId)  || !storage.getUsersTable().containsKey(followerId) || !storage.getUsersTable().containsKey(followeeId)) {
            System.out.println("Invalid input");
            return;
        }
        else {
            User follower = storage.getUsersTable().get(followerId);
            User followee = storage.getUsersTable().get(followeeId);
            switch (action) {
                case "FOLLOW":
                    follower.getFollowingIds().add(followeeId);
                    System.out.printf("Followed %s\n",followee.getUserName());
                    break;
                case "UNFOLLOW":
                    follower.getFollowingIds().remove(followeeId);
                    System.out.printf("Unfollowed %s\n",followee.getUserName());
                    break;
                default:
                    System.out.println("Unknown Action");
                    break;
            }

        }
    }

    public void uploadPost(Long userId, String postContent) {
        if(!storage.getUsersTable().containsKey(userId)) {
            System.out.println("Invalid User");
            return;
        }
        if(isEmpty(postContent)) {
            System.out.println("Post content Empty");
            return;
        }
        Post post = new Post();
        post.setPost(postContent);
        post.setUserId(userId);

        storage.getPostsTable().put(post.getId(),post);
        System.out.printf("Upload Successful with post id: %s\n",post.getId());
    }
}
