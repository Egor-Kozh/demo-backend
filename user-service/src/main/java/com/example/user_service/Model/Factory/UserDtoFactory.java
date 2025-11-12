package com.example.user_service.Model.Factory;

import com.example.user_service.Model.Dto.UserDto;
import com.example.user_service.Model.Entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserDtoFactory {

    public UserDto createUserDto (UserEntity entity){
        return UserDto.builder()
                .userId(entity.getUser_id())
                .firstName(entity.getFirst_name())
                .lastName(entity.getLast_name())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .build();
    }
}
