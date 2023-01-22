package com.kullop.socialMediaAppBackend.services.concretes;

import com.kullop.socialMediaAppBackend.entities.Post;
import com.kullop.socialMediaAppBackend.entities.User;
import com.kullop.socialMediaAppBackend.repositories.concretes.PostDao;
import com.kullop.socialMediaAppBackend.repositories.concretes.UserDao;
import com.kullop.socialMediaAppBackend.requests.PostCreateRequest;
import com.kullop.socialMediaAppBackend.requests.PostUpdateRequest;
import com.kullop.socialMediaAppBackend.services.abstracts.PostProvisionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class PostProvisionManager implements PostProvisionService {
    private PostDao postDao;
    private UserDao userDao;

    public PostProvisionManager(PostDao _postDao, UserDao _userDao){
        this.postDao = _postDao;
        this.userDao = _userDao;
    }

    @Override
    public ResponseEntity<Object> createPost(PostCreateRequest post) {
        Optional<User> user = userDao.getUserById(post.getUserId());
        if(user.isEmpty()){
            HashMap<String, String> response = new HashMap<>();
            response.put("message", "user not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        Post postToSave = new Post(post, user.get());
        Post createdPost = this.postDao.createPost(postToSave);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> updatePostById(PostUpdateRequest postUpdateRequest, Long postId) {
        Optional<Post> post = postDao.getPostById(postId);
        if(post.isEmpty()){
            HashMap<String, String> response = new HashMap<>();
            response.put("message", "post not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        post.get().setContent(postUpdateRequest.getContent());
        post.get().setTitle(postUpdateRequest.getTitle());
        postDao.updatePostById(post.get());

        return new ResponseEntity<>(post.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> deletePostById(Long postId) {
        HashMap<String, String> response = new HashMap<>();
        if(postDao.isPostExists(postId)){
            postDao.deletePostById(postId);
            response.put("message", "success");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        response.put("message", "post not found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
