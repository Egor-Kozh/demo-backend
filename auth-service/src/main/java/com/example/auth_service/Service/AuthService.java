package com.example.auth_service.Service;

import com.example.auth_service.Security.Model.JwtAuthenticationDto;
import com.example.auth_service.Security.Model.RefreshTokenDto;
import com.example.auth_service.Security.Model.UserCredentialsDto;
import com.example.auth_service.Model.Dto.UserDto;
import com.example.auth_service.Model.Entity.UserEntity;
import com.example.auth_service.Model.Factory.UserDtoFactory;
import com.example.auth_service.Model.Factory.UserEntityFactory;
import com.example.auth_service.Repository.UserRepository;
import com.example.auth_service.Security.Jwt.JwtService;
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

        UserEntity createdUser = userEntityFactory.createUserEntity(newUser);

        userRepository.saveAndFlush(createdUser);

        return ResponseEntity.ok().body("User was created");
    }


    public JwtAuthenticationDto singIn(UserCredentialsDto userCredentialsDto) {

        UserEntity user = userRepository.findUserByEmail(userCredentialsDto.getEmail());

        UserDto userDto = userDtoFactory.createUserDto(user);

        if (passwordEncoder.matches(userCredentialsDto.getPassword(), user.getPassword())) {
            return jwtService.generateAuthToken(userDto);
        }

        return null;
    }


    public JwtAuthenticationDto refreshToken(RefreshTokenDto refreshTokenDto) {

        String refreshToken = refreshTokenDto.getRefreshToken();

        if (refreshToken != null && jwtService.validateJwtToken(refreshToken)) {
            UserEntity user = userRepository.findUserByEmail(jwtService.getEmailFromToken(refreshToken));

            UserDto userDto = userDtoFactory.createUserDto(user);

            return jwtService.refreshBaseToken(userDto, refreshToken);
        }

        return null;
    }


}
