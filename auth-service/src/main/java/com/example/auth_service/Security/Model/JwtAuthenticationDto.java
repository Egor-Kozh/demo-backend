package com.example.auth_service.Security.Model;

import lombok.Data;

@Data
public class JwtAuthenticationDto {

    private String accessToken;

    private String refreshToken;

}
