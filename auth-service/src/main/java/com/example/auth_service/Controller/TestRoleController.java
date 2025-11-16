package com.example.auth_service.Controller;

import com.example.auth_service.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "test")
@RequiredArgsConstructor
public class TestRoleController {

    private final AuthService authService;

    @RequestMapping("/user")
    public ResponseEntity<String> testRoleUser() {
        return ResponseEntity.ok().body("Succes");
    }

    @RequestMapping("/admin")
    public ResponseEntity<String> testRoleAdmin() {
        return ResponseEntity.ok().body("Succes");
    }

}