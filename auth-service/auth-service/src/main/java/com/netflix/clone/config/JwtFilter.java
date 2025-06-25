package com.netflix.clone.config;

import java.io.IOException;
import java.util.List;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.netflix.clone.JwtUtil;
import com.netflix.clone.repository.UserRepository;
// import com.netflix.clone.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtFilter extends OncePerRequestFilter {
    
    private final com.netflix.clone.JwtUtil jwtUtil;

    private final UserRepository userRepository;

    public JwtFilter(JwtUtil jwtUtil, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
                                    throws ServletException, IOException{

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer")) {
            String token  = authHeader.substring(7);
            String email = jwtUtil.extractEmail(token);

            UserDetails userDetails = userRepository.findByEmail(email)
            .map(user -> new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), List.of()))
                .orElse(null);
            
                if (userDetails != null ){
                    // Set the authentication in the security context
                    UsernamePasswordAuthenticationToken authentication = 
                            new UsernamePasswordAuthenticationToken(email, userDetails);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    
                }
        }

        filterChain.doFilter(request, response);
    }

}
