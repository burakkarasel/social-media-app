package com.project.app.services;

import com.project.app.entities.Like;
import com.project.app.entities.Post;
import com.project.app.entities.User;
import com.project.app.repos.LikeRepository;
import com.project.app.requests.LikeCreateRequest;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Setter
public class LikeManager implements LikeService{
    private LikeRepository likeRepository;
    private PostService postService;
    private UserService userService;

    @Autowired
    public LikeManager(LikeRepository _likeRepository, PostService _postService, UserService _userService){
        this.likeRepository = _likeRepository;
        this.postService = _postService;
        this.userService = _userService;
    }


    @Override
    public List<Like> getLikes(Optional<Long> postId, Optional<Long> userId) {
        if (postId.isPresent() && userId.isPresent()) {
            return this.likeRepository.findByUserIdAndPostId(userId.get(), postId.get());
        }
        if(postId.isPresent()){
            return this.likeRepository.findByPostId(postId.get());
        }
        if(userId.isPresent()){
            return this.likeRepository.findByUserId(userId.get());
        }

        return this.likeRepository.findAll();
    }

    @Override
    public Like createLike(LikeCreateRequest newLike) {
        Post postToGet = this.postService.getPostById(newLike.getPostId());
        User userToGet = this.userService.getUserById(newLike.getUserId());
        if(postToGet != null && userToGet != null){
            Like toSave = new Like();
            toSave.setPost(postToGet);
            toSave.setUser(userToGet);

            return this.likeRepository.save(toSave);
        }
        return null;
    }

    @Override
    public Like getLikeById(Long id) {
        Optional<Like> likeToGet = this.likeRepository.findById(id);
        return likeToGet.orElse(null);
    }

    @Override
    public void deleteLikeByID(Long id) {
        Optional<Like> likeToGet = this.likeRepository.findById(id);
        if(likeToGet.isPresent()){
            this.likeRepository.deleteById(likeToGet.get().getId());
        }
    }
}
