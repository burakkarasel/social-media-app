package com.kullop.socialMediaAppBackend.controllers;

import com.kullop.socialMediaAppBackend.services.abstracts.LikeQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/likes")
public class LikeQueryController {
    private LikeQueryService likeQueryService;

    public LikeQueryController(LikeQueryService _likeQueryService){
        this.likeQueryService = _likeQueryService;
    }

    @GetMapping
    public ResponseEntity<Object> getLikes(@RequestParam Optional<Long> postId, @RequestParam Optional<Long> userId){
        if(postId.isPresent() && userId.isPresent()){
            return this.likeQueryService.getLikesByPostIdAndUserId(postId.get(), userId.get());
        }

        if(postId.isPresent()){
            return this.likeQueryService.getLikesByPostId(postId.get());
        }

        if(userId.isPresent()){
            return this.likeQueryService.getLikesByUserId(userId.get());
        }

        return this.likeQueryService.getAllLikes();
    }

    @GetMapping("/{likeId}")
    public ResponseEntity<Object> getLikeById(@PathVariable Long likeId){
        return this.likeQueryService.getLikeById(likeId);
    }
}
