package com.project.app.services;

import com.project.app.entities.Like;
import com.project.app.entities.Post;
import com.project.app.entities.User;
import com.project.app.repos.LikeRepository;
import com.project.app.repos.PostRepository;
import com.project.app.repos.UserRepository;
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
    private PostRepository postRepository;
    private UserRepository userRepository;

    @Autowired
    public LikeManager(LikeRepository _likeRepository, PostRepository _postRepository, UserRepository _userRepository){
        this.likeRepository = _likeRepository;
        this.postRepository = _postRepository;
        this.userRepository = _userRepository;
    }


    @Override
    public List<Like> getLikes(Optional<Long> postId, Optional<Long> userId) {
        if(postId.isPresent()){
            Long postIdToGet = postId.get();
            Optional<Post> postToGet = this.postRepository.findById(postIdToGet);
            if(postToGet.isPresent()){
                return this.likeRepository.findByPostId(postIdToGet);
            }
        }

        if(userId.isPresent()){
            Long userIdToGet = userId.get();
            Optional<User> userToGet = this.userRepository.findById(userIdToGet);
            if(userToGet.isPresent()){
                return this.likeRepository.findByUserId(userIdToGet);
            }
        }

        return this.likeRepository.findAll();
    }

    @Override
    public Like createLike(LikeCreateRequest newLike) {
        Optional<Post> postToGet = this.postRepository.findById(newLike.getPostId());
        Optional<User> userToGet = this.userRepository.findById(newLike.getUserId());
        if(postToGet.isPresent() && userToGet.isPresent()){
            Post gotPost = postToGet.get();
            User gotUser = userToGet.get();

            Like toSave = new Like();

            toSave.setPost(gotPost);
            toSave.setUser(gotUser);

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
