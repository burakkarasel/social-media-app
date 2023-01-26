package com.kullop.socialMediaAppBackend.responses;

import com.kullop.socialMediaAppBackend.entities.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private Long id;
    private Long userId;
    private String username;
    private String title;
    private String content;

    public PostResponse(Post post){
        this.content = post.getContent();
        this.title = post.getTitle();
        this.username = post.getUser().getUsername();
        this.id = post.getId();
        this.userId = post.getUser().getId();
    }
}
