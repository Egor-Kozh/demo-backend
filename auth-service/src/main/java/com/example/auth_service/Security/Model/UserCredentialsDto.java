package com.example.auth_service.Security.Model;

import lombok.Data;

@Data
public class UserCredentialsDto
{
    private String email;
    private String password;
}
