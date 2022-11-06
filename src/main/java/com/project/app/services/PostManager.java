package com.project.app.services;

import com.project.app.entities.Post;
import com.project.app.entities.User;
import com.project.app.repos.PostRepository;
import com.project.app.requests.PostCreateRequest;
import com.project.app.requests.PostUpdateRequest;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Setter
public class PostManager implements PostService{

    private PostRepository postRepository;
    private UserService userService;

    public PostManager(PostRepository _postRepository, UserService _userService){
        this.postRepository = _postRepository;
        this.userService = _userService;
    }

    @Override
    public List<Post> getAllPosts(Optional<Long> userId) {
        if(userId.isPresent()){
            return this.postRepository.findByUserId(userId.get());
        }
        return this.postRepository.findAll();
    }

    @Override
    public Post getPostById(Long postId) {
        return this.postRepository.findById(postId).orElse(null);
    }

    @Override
    public Post createPost(PostCreateRequest newPost) {
        User user = userService.getUserById(newPost.getUser_id());
        if(user == null){
            return null;
        }
        Post toSave = new Post();
        toSave.setText(newPost.getText());
        toSave.setTitle(newPost.getTitle());
        toSave.setUser(user);
        return this.postRepository.save(toSave);
    }

    @Override
    public void deletePostById(Long postId) {
        this.postRepository.deleteById(postId);
    }

    @Override
    public Post updatePostById(Long postId, PostUpdateRequest updatedPost) {
        Optional<Post> oldPost = this.postRepository.findById(postId);
        if(oldPost.isPresent()){
            Post toUpdate = oldPost.get();
            toUpdate.setTitle(updatedPost.getTitle());
            toUpdate.setText(updatedPost.getText());

            this.postRepository.deleteById(postId);
            return this.postRepository.save(toUpdate);
        }
        return null;
    }
}
