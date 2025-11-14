package com.example.user_service.Model.Dto;

import com.example.user_service.Model.Entity.Roles;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class UserDto {

    private UUID userId;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private String email;

    @NonNull
    private String password;

    private Roles role;

    private LocalDate createdAt;
}
