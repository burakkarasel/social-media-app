package com.project.app.services;

import com.project.app.entities.Comment;
import com.project.app.requests.CommentCreateRequest;
import com.project.app.requests.CommentUpdateRequest;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    public List<Comment> getComments(Optional<Long> postId, Optional<Long> userId);
    public Comment createComment(CommentCreateRequest newComment);

    public Comment getCommentById(Long id);

    public Comment updateCommentById(Long commentId, CommentUpdateRequest newComment);

    public void deleteCommentById(Long commentId);
}
