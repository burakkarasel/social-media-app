package com.kullop.socialMediaAppBackend.entities;

import com.kullop.socialMediaAppBackend.requests.UserRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    public User(UserRequest userRequest){
        this.setUsername(userRequest.getUsername());
        this.setPassword(userRequest.getPassword());
    }
}
