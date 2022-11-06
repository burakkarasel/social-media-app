package com.project.app.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommentCreateRequest {
    private Long postId;
    private String text;
    private Long userId;
}
