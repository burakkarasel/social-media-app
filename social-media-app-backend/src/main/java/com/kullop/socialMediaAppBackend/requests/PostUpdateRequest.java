package com.kullop.socialMediaAppBackend.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostUpdateRequest {
    private String content;
    private String title;
}
