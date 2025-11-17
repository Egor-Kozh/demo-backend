package com.example.auth_service.Service;

import com.example.auth_service.Model.Dto.UserDto;
import com.example.auth_service.Model.Entity.UserEntity;
import com.example.auth_service.Model.Factory.UserDtoFactory;
import com.example.auth_service.Model.Factory.UserEntityFactory;
import com.example.auth_service.Repository.UserRepository;
import com.example.auth_service.Security.Jwt.JwtService;
import com.example.auth_service.Security.Model.RefreshTokenDto;
import com.example.auth_service.Security.Model.UserCredentialsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final UserEntityFactory userEntityFactory;

    private final UserDtoFactory userDtoFactory;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;


    public ResponseEntity<String> createUser(UserDto newUser) {
        try {
            UserEntity user = userRepository.findUserByEmail(newUser.getEmail());

            if (user != null) {
                throw new RuntimeException("The user with this email already exists!");
            }

            UserEntity createdUser = userEntityFactory.createUserEntity(newUser);

            userRepository.saveAndFlush(createdUser);

            return ResponseEntity.ok().body("User was created");

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    public ResponseEntity<?> signIn(UserCredentialsDto userCredentialsDto) {
        try {
            UserEntity user = userRepository.findUserByEmail(userCredentialsDto.getEmail());

            if (user == null) {
                throw new RuntimeException("Invalid email or password!");
            }

            if (!passwordEncoder.matches(userCredentialsDto.getPassword(), user.getPassword())) {
                throw new RuntimeException("Invalid email or password!");
            }

            UserDto userDto = userDtoFactory.createUserDto(user);

            return ResponseEntity.ok().body(jwtService.generateAuthToken(userDto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    public ResponseEntity<?> refreshToken(RefreshTokenDto refreshTokenDto) {
        try {
            String refreshToken = refreshTokenDto.getRefreshToken();

            if (refreshToken == null) {
                throw new RuntimeException("Invalid refresh token!");
            }

            if (!jwtService.validateJwtToken(refreshToken)) {
                throw new RuntimeException("No valid refresh token!");
            }

            UserEntity user = userRepository.findUserByEmail(jwtService.getEmailFromToken(refreshToken));

            UserDto userDto = userDtoFactory.createUserDto(user);

            return ResponseEntity.ok().body(jwtService.refreshBaseToken(userDto, refreshToken));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
