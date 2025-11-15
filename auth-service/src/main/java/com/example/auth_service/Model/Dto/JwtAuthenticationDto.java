package com.example.auth_service.Model.Dto;

import lombok.Data;

@Data
public class JwtAuthenticationDto {

    private String accessToken;
    private String refreshToken;

}
