package com.kullop.socialMediaAppBackend.responses;

import com.kullop.socialMediaAppBackend.entities.Like;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LikeResponse {
    private Long id;
    private Long userId;
    private String username;
    private Long postId;

    public LikeResponse(Like like){
        this.id = like.getId();
        this.username = like.getUser().getUsername();
        this.userId = like.getUser().getId();
        this.postId = like.getPost().getId();
    }
}
