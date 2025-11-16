package com.example.auth_service.Controller;

import com.example.auth_service.Model.Dto.JwtAuthenticationDto;
import com.example.auth_service.Model.Dto.RefreshTokenDto;
import com.example.auth_service.Model.Dto.UserCredentialsDto;
import com.example.auth_service.Model.Dto.UserDto;
import com.example.auth_service.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody UserDto newUser) {
        return authService.createUser(newUser);
    }

    @RequestMapping("/sing-in")
    public ResponseEntity<JwtAuthenticationDto> singIn(@RequestBody UserCredentialsDto userCredentialsDto) {
        return ResponseEntity.ok().body(authService.singIn(userCredentialsDto));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationDto> refreshToken(@RequestBody RefreshTokenDto refreshTokenDto) {
        return ResponseEntity.ok().body(authService.refreshToken(refreshTokenDto));
    }

    @RequestMapping("/test/user")
    public ResponseEntity<String> testRoleUser() {
        return ResponseEntity.ok().body("Succes");
    }

    @RequestMapping("/test/admin")
    public ResponseEntity<String> testRoleAdmin() {
        return ResponseEntity.ok().body("Succes");
    }

}
