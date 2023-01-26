package com.kullop.socialMediaAppBackend.utils;

import com.kullop.socialMediaAppBackend.entities.Comment;
import com.kullop.socialMediaAppBackend.entities.Like;
import com.kullop.socialMediaAppBackend.entities.Post;
import com.kullop.socialMediaAppBackend.entities.User;
import com.kullop.socialMediaAppBackend.responses.CommentResponse;
import com.kullop.socialMediaAppBackend.responses.LikeResponse;
import com.kullop.socialMediaAppBackend.responses.PostResponse;
import com.kullop.socialMediaAppBackend.responses.UserResponse;

import java.util.ArrayList;
import java.util.List;

public class ResponseConverter {
    public static List<CommentResponse> commentConverter(List<Comment> comments){
        List<CommentResponse> commentResponses = new ArrayList<>();
        for (Comment c: comments) {
            commentResponses.add(new CommentResponse(c));
        }
        return commentResponses;
    };

    public static List<LikeResponse> likeConverter(List<Like> likes){
        List<LikeResponse> likeResponses = new ArrayList<>();
        for (Like l : likes) {
            likeResponses.add(new LikeResponse(l));
        }
        return likeResponses;
    }

    public static List<PostResponse> postConverter(List<Post> posts){
        List<PostResponse> postResponses = new ArrayList<>();
        for (Post p:posts) {
            postResponses.add(new PostResponse(p));
        }
        return postResponses;
    }

    public static List<UserResponse> userConverter(List<User> users){
        List<UserResponse> userResponses = new ArrayList<>();
        for (User u: users) {
            userResponses.add(new UserResponse(u));
        }
        return userResponses;
    }

}
