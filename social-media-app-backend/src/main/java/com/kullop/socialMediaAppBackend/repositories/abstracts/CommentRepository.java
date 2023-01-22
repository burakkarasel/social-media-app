package com.kullop.socialMediaAppBackend.repositories.abstracts;

import com.kullop.socialMediaAppBackend.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
