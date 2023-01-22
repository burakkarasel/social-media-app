package com.kullop.socialMediaAppBackend.repositories.abstracts;

import com.kullop.socialMediaAppBackend.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
}
