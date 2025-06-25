package com.netflix.clone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private String username;
    private String role;

    // // Default constructor
    // public JwtResponse() {
    //     this.token = "";
    //     this.username = "";
    //     this.role = "";
    // }

    // // Constructor with parameters
    // public JwtResponse(String token, String username, String role) {
    //     this.token = token;
    //     this.username = username;
    //     this.role = role;
    // }

}
