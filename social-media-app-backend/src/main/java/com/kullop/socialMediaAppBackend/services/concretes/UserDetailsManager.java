package com.kullop.socialMediaAppBackend.services.concretes;

import com.kullop.socialMediaAppBackend.entities.User;
import com.kullop.socialMediaAppBackend.repositories.concretes.UserDao;
import com.kullop.socialMediaAppBackend.security.JwtUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsManager implements UserDetailsService {

    private UserDao userDao;

    public UserDetailsManager(UserDao _userDao){
        this.userDao = _userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userDao.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("user not found");
        }
        return JwtUserDetails.create(user);
    }

    public UserDetails loadUserById(Long id){
        Optional<User> user = this.userDao.getUserById(id);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("user not found");
        }
        return JwtUserDetails.create(user.get());
    }
}
