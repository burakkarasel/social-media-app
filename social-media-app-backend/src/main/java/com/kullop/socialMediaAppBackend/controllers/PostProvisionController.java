package com.kullop.socialMediaAppBackend.controllers;

import com.kullop.socialMediaAppBackend.requests.PostCreateRequest;
import com.kullop.socialMediaAppBackend.requests.PostUpdateRequest;
import com.kullop.socialMediaAppBackend.services.abstracts.PostProvisionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
public class PostProvisionController {
    private PostProvisionService postProvisionService;

    public PostProvisionController(PostProvisionService _postProvisionService){
        this.postProvisionService = _postProvisionService;
    }

    @PostMapping
    public ResponseEntity<Object> createPost(@RequestBody PostCreateRequest post){
        return this.postProvisionService.createPost(post);
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<Object> updatePostById(@RequestBody PostUpdateRequest postUpdateRequest, @PathVariable Long postId){
        return this.postProvisionService.updatePostById(postUpdateRequest, postId);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Object> deletePostById(@PathVariable Long postId){
        return this.postProvisionService.deletePostById(postId);
    }
}
