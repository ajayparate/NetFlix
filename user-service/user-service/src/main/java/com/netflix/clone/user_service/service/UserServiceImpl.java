package com.netflix.clone.user_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.netflix.clone.user_service.dto.UserDTO;
import com.netflix.clone.user_service.exception.ResourceNotFoundException;
import com.netflix.clone.user_service.model.User;
import com.netflix.clone.user_service.repository.UserRepository;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserServiceInterface{

    private final UserRepository userRepository;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException("Email already exists: " + userDTO.getEmail());

        }
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        User savedUser = userRepository.save(user);
        userDTO.setId(savedUser.getId());
        return userDTO;
        
    }

    @Override
    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        // throw new UnsupportedOperationException("Unimplemented method 'getUserById'");
        return toDTO(user);
    }

    @Override
    public UserDTO updateUser(Long userId, UserDTO userDTO) {
        
        // throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
        User user = userRepository.findById(userId)
            .orElseThrow(()->new ResourceNotFoundException("User Not Found with id: " + userId));

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setMobileNumber(userDTO.getMobileNumber());
        user.setAddress(userDTO.getAddress());
        // User updatedUser = userRepository.save(user);
        return toDTO(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
        // throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
    }

    @Override
    public boolean isEmailExists(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
            
        }else if (!email.contains("@")) {
            throw new IllegalArgumentException("Email must contain '@' character");
        }
        userRepository.existsByEmail(email);
        throw new UnsupportedOperationException("Unimplemented method 'isEmailExists'");
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
        .map(this::toDTO).collect(Collectors.toList());
        // throw new UnsupportedOperationException("Unimplemented method 'getAllUsers'");
    }

    private UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }
    
    
}
