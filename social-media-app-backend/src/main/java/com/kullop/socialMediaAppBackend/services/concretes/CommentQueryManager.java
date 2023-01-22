package com.kullop.socialMediaAppBackend.services.concretes;

import com.kullop.socialMediaAppBackend.entities.Comment;
import com.kullop.socialMediaAppBackend.repositories.concretes.CommentDao;
import com.kullop.socialMediaAppBackend.repositories.concretes.PostDao;
import com.kullop.socialMediaAppBackend.repositories.concretes.UserDao;
import com.kullop.socialMediaAppBackend.services.abstracts.CommentQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class CommentQueryManager implements CommentQueryService {
    private CommentDao commentDao;
    private UserDao userDao;
    private PostDao postDao;

    public CommentQueryManager(CommentDao _commentDao, UserDao _userDao, PostDao _postDao){
        this.commentDao = _commentDao;
        this.userDao = _userDao;
        this.postDao = _postDao;
    }

    @Override
    public ResponseEntity<Object> getAllComments() {
        List<Comment> comments = this.commentDao.getAllComments();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getCommentById(Long id) {
        Optional<Comment> comment = this.commentDao.getCommentById(id);
        if(comment.isEmpty()){
            HashMap<String,String> response = new HashMap<>();
            response.put("message", "comment not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(comment.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getCommentsByPostId(Long postId) {
        if(this.postDao.isPostExists(postId)){
            List<Comment> comments = this.commentDao.getCommentsByPostId(postId);
            return new ResponseEntity<>(comments, HttpStatus.OK);
        }

        HashMap<String,String> response = new HashMap<>();
        response.put("message", "post not found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Object> getCommentsByUserId(Long userId) {
        if(this.userDao.isUserExist(userId)){
            List<Comment> comments = this.commentDao.getCommentsByUserId(userId);
            return new ResponseEntity<>(comments, HttpStatus.OK);
        }

        HashMap<String,String> response = new HashMap<>();
        response.put("message", "user not found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Object> getCommentsByPostIdAndUserId(Long postId, Long userId) {
        if(!this.postDao.isPostExists(postId)){
            HashMap<String,String> response = new HashMap<>();
            response.put("message", "post not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        if(!this.userDao.isUserExist(userId)){
            HashMap<String,String> response = new HashMap<>();
            response.put("message", "user not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        List<Comment> comments = this.commentDao.getCommentsByPostIdAndUserId(postId, userId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
