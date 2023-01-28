package com.kullop.socialMediaAppBackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kullop.socialMediaAppBackend.requests.PostCreateRequest;
import com.kullop.socialMediaAppBackend.requests.PostUpdateRequest;
import com.kullop.socialMediaAppBackend.responses.PostResponse;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    @NotNull
    private String title;

    @Lob
    @Column(columnDefinition = "text")
    @NotNull
    private String content;

    public Post(PostCreateRequest postCreateRequest, User _user){
        this.content = postCreateRequest.getContent();
        this.title = postCreateRequest.getTitle();
        this.user = _user;
    }

    public Post(PostResponse postResponse){
        this.id = postResponse.getId();
        this.content = postResponse.getContent();
        this.title = postResponse.getTitle();
    }
}
