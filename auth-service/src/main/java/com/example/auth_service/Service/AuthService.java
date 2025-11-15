package com.example.auth_service.Service;

import com.example.auth_service.Model.Dto.JwtAuthenticationDto;
import com.example.auth_service.Model.Dto.RefreshTokenDto;
import com.example.auth_service.Model.Dto.UserCredentialsDto;
import com.example.auth_service.Model.Dto.UserDto;
import com.example.auth_service.Model.Entity.Roles;
import com.example.auth_service.Model.Entity.UserEntity;
import com.example.auth_service.Repository.UserRepository;
import com.example.auth_service.Security.Jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public ResponseEntity<String> createUser(UserDto newUser){
        UserEntity createdUser = UserEntity.builder()
                .firstName(newUser.getFirstName())
                .lastName(newUser.getLastName())
                .email(newUser.getEmail())
                .password(passwordEncoder.encode(newUser.getPassword()))
                .role(newUser.getRole() != null ? newUser.getRole() : Roles.USER)
                .createdAt(LocalDate.now())
                .build();

        userRepository.saveAndFlush(createdUser);

        System.out.println(newUser);

        return ResponseEntity.ok().body("User was created");
    }

    public JwtAuthenticationDto singIn(UserCredentialsDto userCredentialsDto){

        UserEntity user = userRepository.findUserByEmail(userCredentialsDto.getEmail());

        if(passwordEncoder.matches(userCredentialsDto.getPassword(), user.getPassword())){
            return jwtService.generateAuthToken(user.getEmail());
        }

        return null;
    }

    public JwtAuthenticationDto refreshToken(RefreshTokenDto refreshTokenDto){
        String refreshToken = refreshTokenDto.getRefreshToken();
        if(refreshToken != null && jwtService.validateJwtToken(refreshToken)){
            UserEntity user = userRepository.findUserByEmail(jwtService.getEmailFromToken(refreshToken));
            return  jwtService.refreshBaseToken(user.getEmail(), refreshToken);
        }
        return null;
    }
}
