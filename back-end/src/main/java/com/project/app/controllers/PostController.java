package com.project.app.controllers;

import com.project.app.entities.Post;
import com.project.app.requests.PostCreateRequest;
import com.project.app.requests.PostUpdateRequest;
import com.project.app.services.PostService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
@Setter
public class PostController {
    private PostService postService;

    @Autowired
    public PostController(PostService _postService){
        this.postService = _postService;
    }

    @GetMapping
    public List<Post> getAllPosts(@RequestParam Optional<Long> userId){
        return this.postService.getAllPosts(userId);
    }

    @GetMapping("/{postId}")
    public Post getPostById(@PathVariable Long postId){
        return this.postService.getPostById(postId);
    }

    @PostMapping
    public Post createPost(@RequestBody PostCreateRequest newPost){
        return this.postService.createPost(newPost);
    }

    @PutMapping("/{postId}")
    public Post updatePostById(@PathVariable Long postId, @RequestBody PostUpdateRequest updatedPost){
        return this.postService.updatePostById(postId, updatedPost);
    }

    @DeleteMapping("/{postId}")
    public void deletePostById(@PathVariable Long postId){
        this.postService.deletePostById(postId);
    }
}
