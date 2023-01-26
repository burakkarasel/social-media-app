package com.kullop.socialMediaAppBackend.services.concretes;

import com.kullop.socialMediaAppBackend.repositories.concretes.PostDao;
import com.kullop.socialMediaAppBackend.responses.PostResponse;
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
        List<PostResponse> postResponses;
        if(userId.isEmpty()){
            postResponses = this.postDao.fetchPostsWithLikeCount();
            return new ResponseEntity<>(postResponses, HttpStatus.OK);
        }
        postResponses = this.postDao.fetchPostsWithLikeCountByUserId(userId.get());
        return new ResponseEntity<>(postResponses, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getPostById(Long postId) {
        PostResponse post = this.postDao.fetchPostWithLikeCountByPostId(postId);
        if (post == null) {
            HashMap<String, String> response = new HashMap<>();
            response.put("message", "user not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
}
