package com.example.auth_service.Model.Factory;

import com.example.auth_service.Model.Dto.UserDto;
import com.example.auth_service.Model.Entity.Roles;
import com.example.auth_service.Model.Entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class UserEntityFactory {

    private final PasswordEncoder passwordEncoder;

    public UserEntity createUserEntity(UserDto userDto) {
        return UserEntity.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .role(userDto.getRole() != null ? userDto.getRole() : Roles.USER)
                .createdAt(LocalDate.now())
                .build();
    }
}