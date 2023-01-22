package com.kullop.socialMediaAppBackend.services.concretes;

import com.kullop.socialMediaAppBackend.entities.Post;
import com.kullop.socialMediaAppBackend.repositories.concretes.PostDao;
import com.kullop.socialMediaAppBackend.services.abstracts.PostQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class PostQueryManager implements PostQueryService {
    private PostDao postDao;

    public PostQueryManager(PostDao _postDao){
        this.postDao = _postDao;
    }

    @Override
    public ResponseEntity<Object> getAllPosts(Optional<Long> userId) {
        if(userId.isEmpty()){
            List<Post> posts = this.postDao.getAllPosts();
            return new ResponseEntity<>(posts, HttpStatus.OK);
        }
        List<Post> posts = this.postDao.getAllPostsByUserId(userId.get());
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getPostById(Long postId) {
        Optional<Post> post = this.postDao.getPostById(postId);
        if (post.isEmpty()) {
            HashMap<String, String> response = new HashMap<>();
            response.put("message", "user not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
}
