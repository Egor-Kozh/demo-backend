package com.example.user_service.Service;

import com.example.user_service.Model.Entity.UserEntity;
import com.example.user_service.Model.Factory.UserDtoFactory;
import com.example.user_service.Repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserDtoFactory userDtoFactory;

    public UserService(UserRepository userRepository, UserDtoFactory userDtoFactory) {
        this.userRepository = userRepository;
        this.userDtoFactory = userDtoFactory;
    }

    public ResponseEntity<?> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();

        return ResponseEntity.ok(users.stream().map(userDtoFactory::createUserDto).collect(Collectors.toList()));
    }
}

