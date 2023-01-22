package com.kullop.socialMediaAppBackend.repositories.abstracts;

import com.kullop.socialMediaAppBackend.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
