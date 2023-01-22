package com.kullop.socialMediaAppBackend.services.concretes;

import com.kullop.socialMediaAppBackend.entities.Comment;
import com.kullop.socialMediaAppBackend.entities.Post;
import com.kullop.socialMediaAppBackend.entities.User;
import com.kullop.socialMediaAppBackend.repositories.concretes.CommentDao;
import com.kullop.socialMediaAppBackend.repositories.concretes.PostDao;
import com.kullop.socialMediaAppBackend.repositories.concretes.UserDao;
import com.kullop.socialMediaAppBackend.requests.CommentCreateRequest;
import com.kullop.socialMediaAppBackend.requests.CommentUpdateRequest;
import com.kullop.socialMediaAppBackend.services.abstracts.CommentProvisionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class CommentProvisionManager implements CommentProvisionService {
    private CommentDao commentDao;
    private PostDao postDao;
    private UserDao userDao;

    public CommentProvisionManager(CommentDao _commentDao, UserDao _userDao, PostDao _postDao){
        this.commentDao = _commentDao;
        this.postDao = _postDao;
        this.userDao = _userDao;
    }

    @Override
    public ResponseEntity<Object> createComment(CommentCreateRequest commentCreateRequest) {
        Optional<User> user = this.userDao.getUserById(commentCreateRequest.getUserId());
        Optional<Post> post = this.postDao.getPostById(commentCreateRequest.getPostId());

        if(user.isEmpty()){
            HashMap<String, String> response = new HashMap<>();
            response.put("message", "user not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        if(post.isEmpty()){
            HashMap<String, String> response = new HashMap<>();
            response.put("message", "post not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        Comment comment = this.commentDao.createComment(new Comment(commentCreateRequest, user.get(), post.get()));
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> updateCommentById(Long commentId, CommentUpdateRequest commentUpdateRequest) {
        Optional<Comment> comment = this.commentDao.getCommentById(commentId);
        if(comment.isEmpty()){
            HashMap<String, String> response = new HashMap<>();
            response.put("message", "comment not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        comment.get().setContent(commentUpdateRequest.getContent());
        this.commentDao.updateCommentById(commentId, comment.get());
        return new ResponseEntity<>(comment.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> deleteCommentById(Long commentId) {
        HashMap<String, String> response = new HashMap<>();
        if(commentDao.isCommentExists(commentId)){
            response.put("message", "success");
            this.commentDao.deleteCommentById(commentId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        response.put("message", "comment not found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
