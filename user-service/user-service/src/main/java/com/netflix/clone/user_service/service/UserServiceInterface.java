package com.netflix.clone.user_service.service;

import java.util.List;

import com.netflix.clone.user_service.dto.UserDTO;

public interface UserServiceInterface {

    UserDTO createUser(UserDTO userDTO);
    UserDTO getUserById(Long userId);
    UserDTO updateUser(Long userId, UserDTO userDTO);   
    void deleteUser(Long userId);
    boolean isEmailExists(String email);
    List<UserDTO> getAllUsers();
}
