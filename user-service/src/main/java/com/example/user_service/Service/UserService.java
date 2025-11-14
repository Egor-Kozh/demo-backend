package com.example.user_service.Service;

import com.example.user_service.Model.Dto.UserDto;
import com.example.user_service.Model.Entity.UserEntity;
import com.example.user_service.Model.Factory.UserDtoFactory;
import com.example.user_service.Model.Factory.UserEntityFactory;
import com.example.user_service.Repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserDtoFactory userDtoFactory;

    private final UserEntityFactory userEntityFactory;

    public UserService(UserRepository userRepository, UserDtoFactory userDtoFactory, UserEntityFactory userEntityFactory) {
        this.userRepository = userRepository;
        this.userDtoFactory = userDtoFactory;
        this.userEntityFactory = userEntityFactory;
    }

    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();

        return ResponseEntity.ok(users.stream().map(userDtoFactory::createUserDto).collect(Collectors.toList()));
    }

    public ResponseEntity<UserDto> getUserById(Long userId){
        UserEntity user = userRepository.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        return ResponseEntity.ok(userDtoFactory.createUserDto(user));
    }

    public ResponseEntity<String> createUser(UserDto newUser){
        UserEntity createdUser = userEntityFactory.createUserEntity(newUser);

        userRepository.saveAndFlush(createdUser);

        System.out.println(newUser);

        return ResponseEntity.ok().body("User was created");
    }
}

