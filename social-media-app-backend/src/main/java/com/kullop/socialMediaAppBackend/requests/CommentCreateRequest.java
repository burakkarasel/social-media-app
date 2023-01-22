package com.kullop.socialMediaAppBackend.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentCreateRequest {
    private Long userId;
    private Long postId;
    private String content;
}
