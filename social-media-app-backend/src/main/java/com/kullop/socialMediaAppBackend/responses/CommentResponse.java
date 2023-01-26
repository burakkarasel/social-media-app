package com.kullop.socialMediaAppBackend.responses;

import com.kullop.socialMediaAppBackend.entities.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {
    private Long id;
    private Long userId;
    private String username;
    private Long postId;
    private String content;

    public CommentResponse(Comment comment){
        this.id = comment.getId();
        this.content = comment.getContent();
        this.username = comment.getUser().getUsername();
        this.postId = comment.getPost().getId();
        this.userId = comment.getUser().getId();
    }
}
