package com.project.app.services;

import com.project.app.entities.Post;
import com.project.app.requests.PostCreateRequest;
import com.project.app.requests.PostUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface PostService{
    public List<Post> getAllPosts(Optional<Long> userId);

    public Post getPostById(Long postId);

    public Post createPost(PostCreateRequest newPost);

    public void deletePostById(Long postId);

    public Post updatePostById(Long postId, PostUpdateRequest updatedPost);
}
