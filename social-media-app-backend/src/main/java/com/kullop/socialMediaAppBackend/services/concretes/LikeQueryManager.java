package com.kullop.socialMediaAppBackend.services.concretes;

import com.kullop.socialMediaAppBackend.entities.Like;
import com.kullop.socialMediaAppBackend.repositories.concretes.LikeDao;
import com.kullop.socialMediaAppBackend.repositories.concretes.PostDao;
import com.kullop.socialMediaAppBackend.repositories.concretes.UserDao;
import com.kullop.socialMediaAppBackend.responses.LikeResponse;
import com.kullop.socialMediaAppBackend.services.abstracts.LikeQueryService;
import com.kullop.socialMediaAppBackend.utils.ResponseConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class LikeQueryManager implements LikeQueryService {
    private LikeDao likeDao;
    private UserDao userDao;
    private PostDao postDao;

    public LikeQueryManager(LikeDao _likeDao, UserDao _userDao, PostDao _postDao){
        this.likeDao = _likeDao;
        this.postDao = _postDao;
        this.userDao = _userDao;
    }


    @Override
    public ResponseEntity<Object> getAllLikes() {
        List<Like> likes = this.likeDao.getAllLikes();
        List<LikeResponse> likeResponses = ResponseConverter.likeConverter(likes);
        return new ResponseEntity<>(likeResponses, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getLikesByPostId(Long postId) {
        if(!this.postDao.isPostExists(postId)){
            HashMap<String , String > response = new HashMap<>();
            response.put("message", "post not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        List<Like> likes = this.likeDao.getLikesByPostId(postId);
        List<LikeResponse> likeResponses = ResponseConverter.likeConverter(likes);
        return new ResponseEntity<>(likeResponses, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getLikesByUserId(Long userId) {
        if(!this.userDao.isUserExist(userId)){
            HashMap<String , String > response = new HashMap<>();
            response.put("message", "user not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        List<Like> likes = this.likeDao.getLikesByUserId(userId);
        List<LikeResponse> likeResponses = ResponseConverter.likeConverter(likes);
        return new ResponseEntity<>(likeResponses, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getLikesByPostIdAndUserId(Long postId, Long userId) {
        if(!this.postDao.isPostExists(postId)){
            HashMap<String , String > response = new HashMap<>();
            response.put("message", "post not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        if(!this.userDao.isUserExist(userId)){
            HashMap<String , String > response = new HashMap<>();
            response.put("message", "user not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        List<Like> likes = this.likeDao.getLikesByPostIdAndUserId(postId, userId);
        List<LikeResponse> likeResponses = ResponseConverter.likeConverter(likes);
        return new ResponseEntity<>(likeResponses, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getLikeById(Long id) {
        Optional<Like> like = this.likeDao.getLikeById(id);

        if(like.isEmpty()){
            HashMap<String , String > response = new HashMap<>();
            response.put("message", "like not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new LikeResponse(like.get()), HttpStatus.OK);
    }
}
