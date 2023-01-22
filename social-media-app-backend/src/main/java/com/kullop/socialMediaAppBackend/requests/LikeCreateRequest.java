package com.kullop.socialMediaAppBackend.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikeCreateRequest {
    private Long userId;
    private Long postId;
}
