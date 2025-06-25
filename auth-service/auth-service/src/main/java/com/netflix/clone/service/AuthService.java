package com.netflix.clone.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.netflix.clone.JwtUtil;
import com.netflix.clone.dto.JwtResponse;
import com.netflix.clone.dto.LoginRequest;
import com.netflix.clone.dto.RegisterRequest;
import com.netflix.clone.model.User;
import com.netflix.clone.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    public String register(RegisterRequest request){
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setAddress(request.getAddress());
        user.setCity(request.getCity());
        user.setState(request.getState());
        user.setZipCode(request.getZipCode());
        user.setRole("USER"); // Default role, can be changed later
        userRepository.save(user);
        return "User registered successfully";
    }

    public JwtResponse login(LoginRequest request){
        User user = userRepository.findByEmail(request.getEmail())
        .orElseThrow(() -> new UsernameNotFoundException("User not Found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) 
            throw new BadCredentialsException("Invalide Credentials");
              

        String token = jwtUtil.generateToken(user.getEmail());
        return new JwtResponse(token, user.getUsername(), user.getRole());
    }

}
