package com.example.user_service.Model.Factory;

import com.example.user_service.Model.Dto.UserDto;
import com.example.user_service.Model.Entity.Roles;
import com.example.user_service.Model.Entity.UserEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UserEntityFactory {

    public UserEntity createUserEntity(UserDto userDto) {
        return UserEntity.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .role(userDto.getRole() != null ? userDto.getRole() : Roles.USER)
                .build();
    }
}
