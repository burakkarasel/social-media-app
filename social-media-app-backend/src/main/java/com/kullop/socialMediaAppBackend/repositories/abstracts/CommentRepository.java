package com.kullop.socialMediaAppBackend.repositories.abstracts;

import com.kullop.socialMediaAppBackend.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentsByPostId(Long postId);
    List<Comment> findCommentsByUserId(Long userId);

    List<Comment> findCommentsByPostIdAndUserId(Long postId, Long userId);

    @Modifying
    @Query("update Comment c set c.content = :content where c.id = :commentId")
    void UpdateCommentById(@Param("commentId") Long commentId, @Param("content") String content);
}
