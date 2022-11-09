package com.project.app.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostCreateRequest {
    private Long id;
    private String text;
    private String title;
    private Long user_id;
}
