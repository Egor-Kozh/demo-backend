package com.example.user_service.Model.Factory;

import com.example.user_service.Model.Dto.UserDto;
import com.example.user_service.Model.Entity.UserEntity;
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
                .build();
    }
}
