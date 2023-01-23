package com.kullop.socialMediaAppBackend.controllers;

import com.kullop.socialMediaAppBackend.requests.LikeCreateRequest;
import com.kullop.socialMediaAppBackend.services.abstracts.LikeProvisionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/likes")
public class LikeProvisionController {
    private LikeProvisionService likeProvisionService;

    public LikeProvisionController(LikeProvisionService _likeProvisionService){
        this.likeProvisionService = _likeProvisionService;
    }

    @PostMapping
    public ResponseEntity<Object> createLike(@RequestBody LikeCreateRequest likeCreateRequest){
        return this.likeProvisionService.createLike(likeCreateRequest);
    }

    @DeleteMapping("/{likeId}")
    public ResponseEntity<Object> deleteLikeById(@PathVariable Long likeId){
        return this.likeProvisionService.deleteLikeById(likeId);
    }
}
