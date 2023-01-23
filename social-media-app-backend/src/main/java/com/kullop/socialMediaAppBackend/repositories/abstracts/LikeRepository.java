package com.kullop.socialMediaAppBackend.repositories.abstracts;

import com.kullop.socialMediaAppBackend.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findLikesByPostId(Long postId);

    List<Like> findLikesByUserId(Long userId);

    List<Like> findLikesByPostIdAndUserId(Long postId, Long userId);
}
