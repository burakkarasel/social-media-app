package com.project.app.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LikeCreateRequest {
    private Long postId;
    private Long userId;
}
