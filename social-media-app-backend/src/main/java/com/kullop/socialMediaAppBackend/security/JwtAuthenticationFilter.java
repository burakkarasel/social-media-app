package com.kullop.socialMediaAppBackend.security;

import com.kullop.socialMediaAppBackend.services.concretes.UserDetailsManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// to add a JWT Auth Filter to our requests
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserDetailsManager userDetailsManager;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            // first we extract the token from request
            String jwtToken = extractJwtFromRequest(request);
            // then we validate it
            if(jwtToken != null && jwtTokenProvider.validateToken(jwtToken)){
                // then we take the id from the token
                Long id = this.jwtTokenProvider.getUserIdFromJwtToken(jwtToken);
                // then we get the user details of the token owner
                UserDetails user = this.userDetailsManager.loadUserById(id);
                if(user != null){
                    // then we create a new auth token with username and password
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
                    // we set its details as WebAuthenticationDetailsSource
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    // then we put the authentication token to Security Context
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }catch (Exception e){
            return;
        }
        filterChain.doFilter(request, response);
    }

    private String extractJwtFromRequest(HttpServletRequest request){
        String bearer = request.getHeader("Authorization");
        if(StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")){
            return bearer.substring(7);
        }
        return null;
    }
}
