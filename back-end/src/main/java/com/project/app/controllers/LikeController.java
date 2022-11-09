package com.project.app.controllers;

import com.project.app.entities.Like;
import com.project.app.requests.LikeCreateRequest;
import com.project.app.services.LikeService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Setter
@RestController
@RequestMapping("/likes")
public class LikeController {
    private LikeService likeService;

    @Autowired
    public LikeController(LikeService _likeService){
        this.likeService = _likeService;
    }

    @GetMapping
    public List<Like> getLikes(@RequestParam Optional<Long> postId, @RequestParam Optional<Long> userId){
        return this.likeService.getLikes(postId, userId);
    }

    @PostMapping
    public Like createLike(@RequestBody LikeCreateRequest newLike){
        return this.likeService.createLike(newLike);
    }

    @GetMapping("/{likeId}")
    public Like getLikeById(@PathVariable Long likeId){
        return this.likeService.getLikeById(likeId);
    }

    @DeleteMapping("/{likeId}")
    public void deleteLikeById(@PathVariable Long likeId){
        this.likeService.deleteLikeByID(likeId);
    }
}
