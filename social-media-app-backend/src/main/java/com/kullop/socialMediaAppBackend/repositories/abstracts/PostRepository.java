package com.kullop.socialMediaAppBackend.repositories.abstracts;

import com.kullop.socialMediaAppBackend.entities.Post;
import com.kullop.socialMediaAppBackend.responses.PostResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findPostsByUserId(Long userId);

    @Modifying
    @Query("update Post p set p.content = :content, p.title = :title where p.id = :id")
    void updatePostById(@Param("id") Long id, @Param("content") String content, @Param("title") String title);

    @Query("select new com.kullop.socialMediaAppBackend.responses.PostResponse(p.id, u.id, u.username, p.title, p.content, count(l)) from Post p left join User u on u.id = p.user.id left join Like l on l.post.id = p.id group by p.id")
    List<PostResponse> fetchPostsWithLikeCount();

    @Query("select new com.kullop.socialMediaAppBackend.responses.PostResponse(p.id, u.id, u.username, p.title, p.content, count(l)) from Post p left join User u on u.id = p.user.id left join Like l on l.post.id = p.id where u.id =:userId group by p.id")
    List<PostResponse> fetchPostsWithLikeCountByUserId(@Param("userId") Long userId);

    @Query("select new com.kullop.socialMediaAppBackend.responses.PostResponse(p.id, u.id, u.username, p.title, p.content, count(l)) from Post p left join User u on u.id = p.user.id left join Like l on l.post.id = p.id where p.id =:postId group by p.id")
    PostResponse fetchPostWithLikeCountByPostId(@Param("postId") Long postId);
}
