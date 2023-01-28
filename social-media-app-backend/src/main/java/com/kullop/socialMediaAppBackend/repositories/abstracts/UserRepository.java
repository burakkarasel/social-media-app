package com.kullop.socialMediaAppBackend.repositories.abstracts;

import com.kullop.socialMediaAppBackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Query("update User u set u.username = :username, u.password = :password where u.id = :id")
    public void updateUserById(@Param("username") String username, @Param("password") String password, @Param("id") Long id);

    User findByUsername(String username);
}
