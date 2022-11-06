package com.project.app.repos;

import com.project.app.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    public List<Like> findByPostId(Long postId);
    public List<Like> findByUserId(Long userId);

    List<Like> findByUserIdAndPostId(Long userId, Long postId);
}
