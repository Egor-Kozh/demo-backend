package com.example.auth_service.Security.Jwt;

import com.example.auth_service.Model.Dto.JwtAuthenticationDto;
import com.example.auth_service.Model.Dto.UserDto;
import com.example.auth_service.Model.Entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.MalformedKeyException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtService {

    private static final Logger LOGGER = LogManager.getLogger(JwtService.class);

    @Value("${jwt.secret}")
    private String jwtSecret;

    public JwtAuthenticationDto generateAuthToken(UserDto user){
        JwtAuthenticationDto jwtDto = new JwtAuthenticationDto();
        jwtDto.setAccessToken(generateJwtToken(user));
        jwtDto.setRefreshToken(generateRefreshToken(user));
        return jwtDto;
    }

    public JwtAuthenticationDto refreshBaseToken(UserDto user, String refreshToken){
        JwtAuthenticationDto jwtDto = new JwtAuthenticationDto();
        jwtDto.setAccessToken(generateJwtToken(user));
        jwtDto.setRefreshToken(refreshToken);
        return jwtDto;
    }

    public String getEmailFromToken(String token){
        Claims claims = Jwts.parser()
                .verifyWith(getSingKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }

    public String getRoleFromToken(String token){
        Claims claims = Jwts.parser()
                .verifyWith(getSingKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.get("role", String.class);
    }

    public Boolean validateJwtToken(String token){
        try{
            Jwts.parser()
                    .verifyWith(getSingKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            return true;
        }catch (ExpiredJwtException expEx){
            LOGGER.error("Expired JwtException", expEx);
        }catch (UnsupportedJwtException expEx){
            LOGGER.error("Unsupported JwtException", expEx);
        }catch (MalformedKeyException expEx){
            LOGGER.error("Malformed JwtException", expEx);
        }catch (SecurityException expEx){
            LOGGER.error("Security JwtException", expEx);
        }catch (Exception expEx){
            LOGGER.error("Invalid JwtException", expEx);
        }
        return false;
    }

    public String generateJwtToken(UserDto user){
        Date date = Date.from(LocalDateTime.now().plusMinutes(5).atZone(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .subject(user.getEmail())
                .claim("role", user.getRole().name())
                .claim("userId", user.getUserId())
                .expiration(date)
                .signWith(getSingKey())
                .compact();
    }

    public String generateRefreshToken(UserDto user){
        Date date = Date.from(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .subject(user.getEmail())
                .claim("role", user.getRole().name())
                .claim("userId", user.getUserId())
                .expiration(date)
                .signWith(getSingKey())
                .compact();
    }

    private SecretKey getSingKey(){
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
