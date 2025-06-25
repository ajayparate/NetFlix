package com.netflix.clone.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.clone.dto.JwtResponse;
import com.netflix.clone.dto.LoginRequest;
// import com.netflix.clone.dto.RegisterRequest;
import com.netflix.clone.service.AuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public String postMethodName(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }
    

    // public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
    //     return ResponseEntity.ok(authService.register(registerRequest));
    // }

    
    // public String postMethodName(@RequestBody String entity) {
    //     //TODO: process POST request
        
    //     return entity;
    // }
    
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }



}
