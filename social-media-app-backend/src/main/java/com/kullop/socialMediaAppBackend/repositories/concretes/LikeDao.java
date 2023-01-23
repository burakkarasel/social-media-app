package com.kullop.socialMediaAppBackend.repositories.concretes;

import com.kullop.socialMediaAppBackend.entities.Like;
import com.kullop.socialMediaAppBackend.repositories.abstracts.LikeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeDao {
    private LikeRepository likeRepository;

    public LikeDao(LikeRepository _likeRepository){
        this.likeRepository = _likeRepository;
    }

    public List<Like> getAllLikes(){
        return this.likeRepository.findAll();
    }

    public Optional<Like> getLikeById(Long id){
        return this.likeRepository.findById(id);
    }

    public Like createLike(Like like){
        return this.likeRepository.save(like);
    }

    public void deleteLikeById(Long id){
        this.likeRepository.deleteById(id);
    }

    public List<Like> getLikesByPostId(Long postId){
        return this.likeRepository.findLikesByPostId(postId);
    }

    public List<Like> getLikesByUserId(Long userId){
        return this.likeRepository.findLikesByUserId(userId);
    }

    public List<Like> getLikesByPostIdAndUserId(Long postId, Long userId){
        return this.likeRepository.findLikesByPostIdAndUserId(postId, userId);
    }

    public Boolean isLikeExists(Long id){
        return this.likeRepository.existsById(id);
    }
}
