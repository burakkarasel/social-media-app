package com.project.app.services;

import com.project.app.entities.Like;
import com.project.app.requests.LikeCreateRequest;

import java.util.List;
import java.util.Optional;

public interface LikeService {
    public List<Like> getLikes(Optional<Long> postId, Optional<Long> userId);
    public Like createLike(LikeCreateRequest newLike);

    public Like getLikeById(Long id);

    public void deleteLikeByID(Long id);
}
