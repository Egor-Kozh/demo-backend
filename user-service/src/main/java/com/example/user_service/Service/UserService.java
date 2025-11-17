package com.example.user_service.Service;

import com.example.user_service.Model.Dto.UserDto;
import com.example.user_service.Model.Entity.UserEntity;
import com.example.user_service.Model.Factory.UserDtoFactory;
import com.example.user_service.Repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserDtoFactory userDtoFactory;

    public UserService(UserRepository userRepository, UserDtoFactory userDtoFactory) {
        this.userRepository = userRepository;
        this.userDtoFactory = userDtoFactory;
    }

    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();

        return ResponseEntity.ok(users.stream().map(userDtoFactory::createUserDto).collect(Collectors.toList()));
    }

    public ResponseEntity<?> getUserById(UUID userId) {
        try {
            UserEntity user = userRepository.getUserById(userId);

            if (user == null) {
                throw new RuntimeException("There is no user with this id!");
            }

            return ResponseEntity.ok(userDtoFactory.createUserDto(user));

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity<?> getUserByEmail(String email) {
        try {
            UserEntity user = userRepository.getUserByEmail(email);

            if (user == null) {
                throw new RuntimeException("There is no user with this email!");
            }

            return ResponseEntity.ok(userDtoFactory.createUserDto(user));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
