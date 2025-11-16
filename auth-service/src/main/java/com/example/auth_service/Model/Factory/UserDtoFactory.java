package com.example.auth_service.Model.Factory;

import com.example.auth_service.Model.Dto.UserDto;
import com.example.auth_service.Model.Entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserDtoFactory {

    public UserDto createUserDto (UserEntity entity){
        return UserDto.builder()
                .userId(entity.getUserId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .role(entity.getRole())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
