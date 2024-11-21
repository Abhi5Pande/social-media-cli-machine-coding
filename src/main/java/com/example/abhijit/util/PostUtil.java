package com.example.abhijit.util;

import com.example.abhijit.entities.Post;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PostUtil {
    public static int customCompare(Post p1, Post p2) {
        if(p1.getScore() != p2.getScore()) {
            return Integer.compare(p2.getScore(), p1.getScore());
        }
        return Long.compare(p2.getCreatedTime(), p1.getCreatedTime());
    }
}
