package com.example.abhijit.service;

import com.example.abhijit.db.Storage;
import com.example.abhijit.entities.Post;
import com.example.abhijit.entities.User;
import com.example.abhijit.util.PostUtil;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PostService {

    public final Storage storage;

    public PostService(Storage storage) {
        this.storage = storage;
    }
    public void interactWithPost(String action, Long userId , String postId) {
        if(!storage.getUsersTable().containsKey(userId)) {
            System.out.println("User not found");
            return;
        }
        if(!storage.getPostsTable().containsKey(postId)) {
            System.out.println("Unknown Post");
            return;
        }
        switch (action) {
            case "LIKE" :
                handleLike(userId,postId);
                break;
            case "DISLIKE":
                handleDislike(userId,postId);
                break;
            default:
                System.out.println("Unknown Action");
                break;
        }
    }



    public void showPosts(Long userId) {
        if(!storage.getUsersTable().containsKey(userId)) {
            System.out.println("Invalid User");
        }
        User user = storage.getUsersTable().get(userId);
        PriorityQueue<Post> followedQueue = new PriorityQueue<Post>(PostUtil::customCompare);
        PriorityQueue<Post> unfollowedQueue = new PriorityQueue<Post>(PostUtil::customCompare);
        for(Post post :storage.getPostsTable().values() ) {
            if(user.getFollowingIds().contains(post.getUserId())) {
                followedQueue.offer(post);
            }
            else {
                unfollowedQueue.offer(post);
            }
        }

        while(!followedQueue.isEmpty()) {
            Post post = followedQueue.poll();
            System.out.printf("UserName - %s\n" +
                    "# of Likes - %s\n" +
                    "# of Dislikes - %s\n" +
                    "Post - %s\n" +
                    "Post time - %s\n\n\n",
                    storage.getUsersTable().get(post.getUserId()).getUserName(),
                    post.getLikedBy().size(),
                    post.getUnlikedBy().size(),
                    post.getPost(),
                    new Date(post.getCreatedTime()));
        }

        while(!unfollowedQueue.isEmpty()) {
            Post post = unfollowedQueue.poll();
            System.out.printf("UserName - %s\n" +
                            "# of Likes - %s\n" +
                            "# of Dislikes - %s\n" +
                            "Post - %s\n" +
                            "Post time - %s\n\n\n",
                    storage.getUsersTable().get(post.getUserId()).getUserName(),
                    post.getLikedBy().size(),
                    post.getUnlikedBy().size(),
                    post.getPost(),
                    new Date(post.getCreatedTime()));
        }


    }

    private void handleLike(Long userId , String postId) {
        Post post = storage.getPostsTable().get(postId);
        post.getUnlikedBy().remove(userId);
        post.getLikedBy().add(userId);
        post.setScore(post.getLikedBy().size() - post.getUnlikedBy().size());
        System.out.println("Post Liked!");
    }

    private void handleDislike(Long userId , String postId) {
        if(!storage.getPostsTable().containsKey(postId)) {
            System.out.println("Unknown Post");
            return;
        }
        Post post = storage.getPostsTable().get(postId);
        post.getLikedBy().remove(userId);
        post.getUnlikedBy().add(userId);
        post.setScore(post.getLikedBy().size() - post.getUnlikedBy().size());
        System.out.println("Post Disliked!");
    }
}
