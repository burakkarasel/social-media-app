package com.kullop.socialMediaAppBackend.controllers;

import com.kullop.socialMediaAppBackend.services.abstracts.PostQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/posts")
public class PostQueryController {
    private PostQueryService postQueryService;

    public PostQueryController(PostQueryService _postQueryService){
        this.postQueryService = _postQueryService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllPostsByUserId(@RequestParam Optional<Long> userId){
        return this.postQueryService.getAllPosts(userId);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Object> getPostById(@PathVariable Long postId){
        return this.postQueryService.getPostById(postId);
    }
}
