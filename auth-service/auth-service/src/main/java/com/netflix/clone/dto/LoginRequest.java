package com.netflix.clone.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String email;
    private String password;

    // public LoginRequest() {
    // }

    // public LoginRequest(String username, String password) {
    //     this.username = username;
    //     this.password = password;
    // }

}
