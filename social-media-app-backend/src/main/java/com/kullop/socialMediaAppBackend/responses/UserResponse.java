package com.kullop.socialMediaAppBackend.responses;

import com.kullop.socialMediaAppBackend.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String username;

    public UserResponse(User user){
        this.id = user.getId();
        this.username = user.getUsername();
    }
}
