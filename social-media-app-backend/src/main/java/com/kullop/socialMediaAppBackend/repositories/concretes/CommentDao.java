package com.kullop.socialMediaAppBackend.repositories.concretes;

import com.kullop.socialMediaAppBackend.entities.Comment;
import com.kullop.socialMediaAppBackend.repositories.abstracts.CommentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentDao {
    private CommentRepository commentRepository;

    public CommentDao(CommentRepository _commentRepository){
        this.commentRepository = _commentRepository;
    }

    public List<Comment> getAllComments(){
        return this.commentRepository.findAll();
    }

    public Optional<Comment> getCommentById(Long commentId){
        return this.commentRepository.findById(commentId);
    }

    public Comment createComment(Comment comment){
        return this.commentRepository.save(comment);
    }

    public List<Comment> getCommentsByPostId(Long postId){
        return this.commentRepository.findCommentsByPostId(postId);
    }

    public List<Comment> getCommentsByUserId(Long userId){
        return this.commentRepository.findCommentsByUserId(userId);
    }

    public List<Comment> getCommentsByPostIdAndUserId(Long postId, Long userId){
        return this.commentRepository.findCommentsByPostIdAndUserId(postId, userId);
    }

    @Transactional
    public void updateCommentById(Long commentId, Comment comment){
        this.commentRepository.UpdateCommentById(commentId, comment.getContent());
    }

    public void deleteCommentById(Long commentId){
        this.commentRepository.deleteById(commentId);
    }

    public Boolean isCommentExists(Long commentId){
        return this.commentRepository.existsById(commentId);
    }
}
