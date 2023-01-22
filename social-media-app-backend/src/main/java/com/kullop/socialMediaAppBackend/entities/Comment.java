package com.kullop.socialMediaAppBackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kullop.socialMediaAppBackend.requests.CommentCreateRequest;
import com.kullop.socialMediaAppBackend.requests.CommentUpdateRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "comments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    @Lob
    @Column(columnDefinition = "text")
    @NotNull
    private String content;

    public Comment(CommentCreateRequest commentCreateRequest, User _user, Post _post){
        this.content = commentCreateRequest.getContent();
        this.post = _post;
        this.user = _user;
    }

    public Comment(CommentUpdateRequest commentUpdateRequest, User _user, Post _post){
        this.content = commentUpdateRequest.getContent();
        this.post = _post;
        this.user = _user;
    }
}
