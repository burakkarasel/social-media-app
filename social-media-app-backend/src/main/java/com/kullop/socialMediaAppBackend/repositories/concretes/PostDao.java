package com.kullop.socialMediaAppBackend.repositories.concretes;

import com.kullop.socialMediaAppBackend.entities.Post;
import com.kullop.socialMediaAppBackend.repositories.abstracts.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostDao {
    private PostRepository postRepository;

    public PostDao(PostRepository _postRepository){
        this.postRepository = _postRepository;
    }

    public Post createPost(Post post){
        return this.postRepository.save(post);
    }

    public Optional<Post> getPostById(Long id){
        return this.postRepository.findById(id);
    }

    public List<Post> getAllPosts(){
        return this.postRepository.findAll();
    }

    public List<Post> getAllPostsByUserId(Long userId){
        return this.postRepository.findPostsByUserId(userId);
    }

    public Boolean isPostExists(Long postId){
        return this.postRepository.existsById(postId);
    }

    @Transactional
    public void updatePostById(Post post){
        this.postRepository.updatePostById(post.getId(), post.getContent(), post.getTitle());
    }

    public void deletePostById(Long postId){
        this.postRepository.deleteById(postId);
    }
}
