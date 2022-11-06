package com.project.app.services;

import com.project.app.entities.Comment;
import com.project.app.entities.Post;
import com.project.app.entities.User;
import com.project.app.repos.CommentRepository;
import com.project.app.repos.PostRepository;
import com.project.app.repos.UserRepository;
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
    private PostRepository postRepository;

    private UserRepository userRepository;

    @Autowired
    public CommentManager(CommentRepository _commentRepository, PostRepository _postRepository, UserRepository _userRepository){
        this.commentRepository = _commentRepository;
        this.postRepository = _postRepository;
        this.userRepository = _userRepository;
    }

    @Override
    public List<Comment> getComments(Optional<Long> postId, Optional<Long> userId) {
        if(postId.isPresent()){
            Long postIdToGet = postId.get();
            Optional<Post> postToGet = this.postRepository.findById(postIdToGet);
            if(postToGet.isPresent()){
                return this.commentRepository.findByPostId(postIdToGet);
            }
        }

        if(userId.isPresent()){
            Long userIdToGet = userId.get();
            Optional< User> userToGet = this.userRepository.findById(userIdToGet);
            if(userToGet.isPresent()){
                return this.commentRepository.findByUserId(userIdToGet);
            }
        }

        return this.commentRepository.findAll();
    }

    @Override
    public Comment createComment(CommentCreateRequest newComment) {
        Optional<Post> postToGet = this.postRepository.findById(newComment.getPostId());
        Optional<User> userToGet = this.userRepository.findById(newComment.getUserId());
        if(postToGet.isPresent() && userToGet.isPresent()){
            Post gotPost = postToGet.get();
            User gotUser = userToGet.get();

            Comment toSave = new Comment();
            toSave.setText(newComment.getText());
            toSave.setPost(gotPost);
            toSave.setUser(gotUser);

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

            this.commentRepository.deleteById(gotComment.getId());
            return this.commentRepository.save(gotComment);
        }
        return null;
    }

    @Override
    public void deleteCommentById(Long commentId) {
        this.commentRepository.deleteById(commentId);
    }
}
