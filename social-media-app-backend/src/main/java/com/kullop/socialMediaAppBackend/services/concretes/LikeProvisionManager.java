package com.kullop.socialMediaAppBackend.services.concretes;

import com.kullop.socialMediaAppBackend.entities.Like;
import com.kullop.socialMediaAppBackend.entities.Post;
import com.kullop.socialMediaAppBackend.entities.User;
import com.kullop.socialMediaAppBackend.repositories.concretes.LikeDao;
import com.kullop.socialMediaAppBackend.repositories.concretes.PostDao;
import com.kullop.socialMediaAppBackend.repositories.concretes.UserDao;
import com.kullop.socialMediaAppBackend.requests.LikeCreateRequest;
import com.kullop.socialMediaAppBackend.services.abstracts.LikeProvisionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class LikeProvisionManager implements LikeProvisionService {
    private LikeDao likeDao;
    private UserDao userDao;
    private PostDao postDao;

    public LikeProvisionManager(LikeDao _likeDao, UserDao _userDao, PostDao _postDao){
        this.likeDao = _likeDao;
        this.postDao = _postDao;
        this.userDao = _userDao;
    }

    @Override
    public ResponseEntity<Object> createLike(LikeCreateRequest likeCreateRequest) {
        Optional<User> user = this.userDao.getUserById(likeCreateRequest.getUserId());
        Optional<Post> post = this.postDao.getPostById(likeCreateRequest.getPostId());

        if(post.isEmpty()){
            HashMap<String , String> response = new HashMap<>();
            response.put("message", "post not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        if(user.isEmpty()){
            HashMap<String , String> response = new HashMap<>();
            response.put("message", "user not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        Like like =this.likeDao.createLike(new Like(user.get(), post.get()));
        return new ResponseEntity<>(like, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> deleteLikeById(Long id) {
        HashMap<String , String> response = new HashMap<>();

        if(!this.likeDao.isLikeExists(id)){
            response.put("message", "like not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        this.likeDao.deleteLikeById(id);
        response.put("message", "success");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
