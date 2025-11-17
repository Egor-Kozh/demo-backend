package com.example.auth_service.Controller;

import com.example.auth_service.Model.Dto.UserDto;
import com.example.auth_service.Security.Model.RefreshTokenDto;
import com.example.auth_service.Security.Model.UserCredentialsDto;
import com.example.auth_service.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody UserDto newUser) {
        return authService.createUser(newUser);
    }

    @RequestMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody UserCredentialsDto userCredentialsDto) {
        return authService.signIn(userCredentialsDto);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenDto refreshTokenDto) {
        return authService.refreshToken(refreshTokenDto);
    }
}
