package com.kullop.socialMediaAppBackend.config;

import com.kullop.socialMediaAppBackend.security.JwtAuthenticationEntryPoint;
import com.kullop.socialMediaAppBackend.security.JwtAuthenticationFilter;
import com.kullop.socialMediaAppBackend.services.concretes.UserDetailsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private UserDetailsManager userDetailsManager;
    private JwtAuthenticationEntryPoint handler;

    private PasswordEncoder passwordEncoder;

    public SecurityConfig(UserDetailsManager _userDetailsManager, JwtAuthenticationEntryPoint _handler){
        this.userDetailsManager = _userDetailsManager;
        this.handler = _handler;
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter();
    }

    @Bean
    public AuthenticationManager ldapAuthenticationManager() throws Exception {
        return super.au
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
