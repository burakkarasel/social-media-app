package com.project.app.services;

import com.project.app.entities.Comment;
import com.project.app.entities.Post;
import com.project.app.entities.User;
import com.project.app.repos.CommentRepository;
import com.project.app.requests.CommentCreateRequest;
import com.project.app.requests.CommentUpdateRequest;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Setter
public class CommentManager implements CommentService{
    private CommentRepository commentRepository;
    private PostService postService;

    private UserService userService;

    @Autowired
    public CommentManager(CommentRepository _commentRepository, PostService _postService, UserService _userService){
        this.commentRepository = _commentRepository;
        this.postService = _postService;
        this.userService = _userService;
    }

    @Override
    public List<Comment> getComments(Optional<Long> postId, Optional<Long> userId) {
        if (postId.isPresent() && userId.isPresent()) {
            return this.commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
        }

        if(postId.isPresent()){
            return this.commentRepository.findByPostId(postId.get());
        }

        if(userId.isPresent()){
            return this.commentRepository.findByUserId(userId.get());
        }

        return this.commentRepository.findAll();
    }

    @Override
    public Comment createComment(CommentCreateRequest newComment) {
        Post postToGet = this.postService.getPostById(newComment.getPostId());
        User userToGet = this.userService.getUserById(newComment.getUserId());
        if(postToGet != null && userToGet != null){
            Comment toSave = new Comment();

            toSave.setText(newComment.getText());
            toSave.setPost(postToGet);
            toSave.setUser(userToGet);

            return this.commentRepository.save(toSave);
        }
        return null;
    }

    @Override
    public Comment getCommentById(Long id) {
        Optional<Comment> commentToGet = this.commentRepository.findById(id);
        return commentToGet.orElse(null);
    }

    @Override
    public Comment updateCommentById(Long commentId ,CommentUpdateRequest newComment) {
        Optional<Comment> commentToGet = this.commentRepository.findById(commentId);
        if (commentToGet.isPresent()) {
            Comment gotComment = commentToGet.get();

            gotComment.setText(newComment.getText());
            return this.commentRepository.save(gotComment);
        }
        return null;
    }

    @Override
    public void deleteCommentById(Long commentId) {
        this.commentRepository.deleteById(commentId);
    }
}
